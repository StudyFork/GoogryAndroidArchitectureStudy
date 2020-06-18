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
import com.project.architecturestudy.data.model.NaverApiData
import com.project.architecturestudy.data.repository.NaverMovieRepository
import com.project.architecturestudy.data.source.local.room.MovieLocalItem
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchViewModel(private val repository: NaverMovieRepository) : BaseViewModel() {

    private val _movieData = MutableLiveData<List<MovieItem>>()
    val movieData: LiveData<List<MovieItem>> = _movieData

    private val _showToast = MutableLiveData<@StringRes Int>()
    val showToast: LiveData<Int> = _showToast

    private val _tvResultVisible = MutableLiveData<Int>().apply { this.value = View.GONE }
    val tvResultVisible: LiveData<Int> = _tvResultVisible

    private val _searchWord = MutableLiveData<String>().apply { this.value = "" }
    val searchWord: LiveData<String> = _searchWord

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
                                title = item.title
                                subtitle = item.subtitle
                                image = item.image
                                link = item.link
                                pubDate = item.pubDate
                                director = item.director
                                actor = item.actor
                                userRating = item.userRating
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
                        { remoteData ->
                            Log.d(customTAG, "getRemoteData:$remoteData")
                            _movieData.value = remoteData.items
                            _showToast.value = R.string.get_data_success

                            repository.deleteMovieList { dao ->
                                Observable.fromCallable { dao.deleteAll() }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                                    .subscribe { saveMovieList(remoteData) }
                            }

                        }, { t ->
                            _showToast.value = R.string.get_data_failure
                            Log.d(customTAG, t.toString())
                        }).addDisposable()
            })

    }

    private fun saveMovieList(data: NaverApiData) {
        for (item in data.items) {
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
                    observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            Log.d(customTAG, "RoomDatabase Save Data Success")
                        }, {
                            Log.d(customTAG, "RoomDatabase Save Data Failure$it")
                        })
                })
        }
    }

    fun invokeTextChanged() {
        _tvResultVisible.value = View.GONE
    }
}