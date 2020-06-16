package com.project.architecturestudy.ui.search

import android.util.Log
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.architecturestudy.R
import com.project.architecturestudy.base.BaseViewModel
import com.project.architecturestudy.components.Constants.customTAG
import com.project.architecturestudy.data.model.MovieItem
import com.project.architecturestudy.data.repository.NaverMovieRepository
import com.project.architecturestudy.data.source.local.room.MovieLocalItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchViewModel(private val repository: NaverMovieRepository) : BaseViewModel() {

    private val _movieData = MutableLiveData<List<MovieItem>>()
    val movieData: LiveData<List<MovieItem>> = _movieData

    private val _showToast = MutableLiveData<@StringRes Int>()
    val showToast: LiveData<Int> = _showToast

    private val _tvResultVisible = MutableLiveData<Int>().apply { this.value = View.GONE }
    val tvResultVisible: LiveData<Int> = _tvResultVisible

    init {
        getMovieListFromLocal()
    }

    private fun getMovieListFromLocal() {
        repository.getCashedMovieList(
            onSuccess = {
                Log.d(customTAG, "getLocalData:$it")
                it.subscribe({ movieLocalItem ->
                    Log.d(customTAG, "RoomDatabase Get Data Success:$movieLocalItem")
                    if (movieLocalItem.isNotEmpty()) {
                        val movieList = ArrayList<MovieItem>()
                        for (item in movieLocalItem) {
                            movieList.add(MovieItem().apply {
                                this.title = item.title
                                this.subtitle = item.subtitle
                                this.image = item.image
                                this.link = item.link
                                this.pubDate = item.pubDate
                                this.director = item.director
                                this.actor = item.actor
                                this.userRating = item.userRating
                            })
                        }

                        _movieData.postValue(movieList)
                        _showToast.postValue(R.string.get_local_data_success)

                    } else {
                        Log.d(customTAG, "RoomDatabase has no Data")
                    }
                },
                    {
                        Log.d(customTAG, "RoomDatabase GetData Failure")
                    }).addDisposable()
            },
            onFailure = {
                Log.d(customTAG, "Throwable:$it")
                _showToast.postValue(R.string.get_local_data_failure)
            })
    }

    fun getMovieListFromRemote(searchWord: String) {
        if (searchWord.isEmpty()) {
            _showToast.value = R.string.please_write
            return
        }

        _tvResultVisible.value = View.VISIBLE
        repository.getMovieList(searchWord,
            onGetRemoteData = { single ->
                single.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            Log.d(customTAG, "getRemoteData:$it")
                            _movieData.value = it.items
                            _showToast.value = R.string.get_data_success

                            for (item in it.items) {
                                val movieLocalItem = MovieLocalItem().apply {
                                    this.title = item.title
                                    this.subtitle = item.subtitle
                                    this.image = item.image
                                    this.link = item.link
                                    this.pubDate = item.pubDate
                                    this.director = item.director
                                    this.actor = item.actor
                                    this.userRating = item.userRating
                                }
                                repository.saveMovieList(movieLocalItem,
                                    onInsert = { observable ->
                                        observable.doOnSubscribe {
                                            repository.deleteMovieList { dao -> dao.deleteAll() }
                                        }
                                            .subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe({
                                                Log.d(customTAG, "RoomDatabase Save Data Success")
                                            }, {
                                                Log.d(customTAG, "RoomDatabase Save Data Failure$it")
                                            })
                                    })
                            }
                        }, { t ->
                            _showToast.value = R.string.get_data_failure
                            Log.d(customTAG, t.toString())
                        }).addDisposable()
            })

    }

    fun invokeTextChanged() {
        _tvResultVisible.value = View.GONE
    }
}