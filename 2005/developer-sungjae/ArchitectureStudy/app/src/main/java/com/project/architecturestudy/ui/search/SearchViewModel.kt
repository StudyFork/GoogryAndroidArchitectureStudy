package com.project.architecturestudy.ui.search

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.architecturestudy.R
import com.project.architecturestudy.base.BaseViewModel
import com.project.architecturestudy.components.Constants.customTAG
import com.project.architecturestudy.provider.ResourceProvider
import com.project.data.NaverMovieRepository
import com.project.data.model.MovieItem
import com.project.data.model.NaverMovieEntity
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
                if (movieLocalItem.items.isNotEmpty()) {
                    _movieData.postValue(movieLocalItem.items)
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
                        .fromCallable { repository.deleteMovieList() }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { saveMovieList(remoteData) }.addDisposable()

                }, { t ->
                    _showToast.value = resourceProvider.getString(R.string.get_data_failure)
                    Log.d(customTAG, t.toString())
                }).addDisposable()
    }

    private fun saveMovieList(data: NaverMovieEntity) {
        repository.saveMovieList(data)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                Log.d(customTAG, "RoomDatabase Save Data Success")
            }, {
                Log.d(customTAG, "RoomDatabase Save Data Failure$it")
            }).addDisposable()
    }

    fun invokeTextChanged() {
        _tvResultVisible.value = View.GONE
    }

}