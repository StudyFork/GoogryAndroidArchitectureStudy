package com.hhi.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hhi.myapplication.base.BaseViewModel
import com.hhi.myapplication.data.model.MovieData
import com.hhi.myapplication.data.repository.NaverRepositoryDataSourceImpl

class MainViewModel : BaseViewModel() {
    private val repositoryDataSourceImpl = NaverRepositoryDataSourceImpl()
    private val _movieList = MutableLiveData<ArrayList<MovieData.MovieItem>>()
    val movieList: LiveData<ArrayList<MovieData.MovieItem>> = _movieList
    private val _emptyQueryEvent = MutableLiveData<Unit>()
    val emptyQueryEvent: LiveData<Unit> = _emptyQueryEvent
    private val _hideKeyBoardEvent = MutableLiveData<Unit>()
    val hideKeyBoardEvent: LiveData<Unit> = _hideKeyBoardEvent
    private val _searchRecentQueryEvent = MutableLiveData<Unit>()
    val searchRecentQueryEvent: LiveData<Unit> = _searchRecentQueryEvent
    val query = MutableLiveData<String>()

    fun searchMovie() {
        _hideKeyBoardEvent.value = Unit
        if (query.value.isNullOrBlank()) {
            _emptyQueryEvent.value = Unit
        } else {
            repositoryDataSourceImpl.saveQuery(query.value!!)

            visible.value = true
            repositoryDataSourceImpl.searchMovies(query.value!!,
                success = {
                    _movieList.value = it.items
                    visible.value = false
                },
                failed = {
                    Log.e("search_failed", it.toString())
                    visible.value = false
                }
            )
        }
    }

    fun searchRecentQuery() {
        _searchRecentQueryEvent.value = Unit
    }
}