package com.project.architecturestudy.ui.search

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.architecturestudy.Provider.ResourceProvider
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

class SearchViewModel(private val repository: NaverMovieRepository, private val resourceProvider: ResourceProvider) : BaseViewModel() {

    private val _movieData = MutableLiveData<List<MovieItem>>()
    val movieData: LiveData<List<MovieItem>> = _movieData

    private val _showToast = MutableLiveData<String>()
    val showToast: LiveData<String> = _showToast

    private val _tvResultVisible = MutableLiveData<Int>().apply { this.value = View.GONE }
    val tvResultVisible: LiveData<Int> = _tvResultVisible

    private val _searchWord = MutableLiveData<String>().apply { this.value = "" }
    val searchWord: LiveData<String> = _searchWord

    init {
        getMovieListFromLocal()
    }

    private fun getMovieListFromLocal() {
        repository.getCashedMovieList().subscribe(
            { movieLocalItem ->
                if (movieLocalItem.isNotEmpty()) {
                    val movieList = translatingToShow(movieLocalItem)
                    _movieData.postValue(movieList)
                    _showToast.postValue(resourceProvider.getString(R.string.get_local_data_success))

                } else {
                    Log.d(customTAG, "RoomDatabase has no Data")
                }
            }, {

            }).addDisposable()
    }

    fun getMovieListFromRemote(searchWord: String) {
        if (searchWord.isEmpty()) {
            _showToast.value = resourceProvider.getString(R.string.please_write)
            return
        }

        _tvResultVisible.value = View.VISIBLE
        repository.getMovieList(searchWord)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { remoteData ->
                    Log.d(customTAG, "getRemoteData:$remoteData")
                    _movieData.value = remoteData.items
                    _showToast.value = resourceProvider.getString(R.string.get_data_success)

                    Observable
                        .fromCallable { repository.deleteMovieList().deleteAll() }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { saveMovieList(remoteData) }.addDisposable()

                }, { t ->
                    _showToast.value = resourceProvider.getString(R.string.get_data_failure)
                    Log.d(customTAG, t.toString())
                }).addDisposable()
    }

    private fun saveMovieList(data: NaverApiData) {
        for (item in data.items) {
            val movieLocalItem = translatingToInsert(item)
            repository.saveMovieList(movieLocalItem)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d(customTAG, "RoomDatabase Save Data Success")
                }, {
                    Log.d(customTAG, "RoomDatabase Save Data Failure$it")
                }).addDisposable()
        }
    }

    private fun translatingToShow(movieLocalItem: List<MovieLocalItem>): ArrayList<MovieItem> {
        val movieList = ArrayList<MovieItem>()
        for (item in movieLocalItem) {
            val movieItem = MovieItem().apply {
                title = item.title
                subtitle = item.subtitle
                image = item.image
                link = item.link
                pubDate = item.pubDate
                director = item.director
                actor = item.actor
                userRating = item.userRating
            }
            movieList.add(movieItem)
        }
        return movieList
    }

    private fun translatingToInsert(item: MovieItem): MovieLocalItem {
        return MovieLocalItem().apply {
            title = item.title
            subtitle = item.subtitle
            image = item.image
            link = item.link
            pubDate = item.pubDate
            director = item.director
            actor = item.actor
            userRating = item.userRating
        }
    }

    fun invokeTextChanged() {
        _tvResultVisible.value = View.GONE
    }

}