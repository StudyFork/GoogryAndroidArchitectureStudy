package com.hwaniiidev.architecture.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hwaniiidev.architecture.data.repository.NaverMovieRepository
import com.hwaniiidev.architecture.model.Item

class MainViewModel(
    val naverMovieRepositoryImpl: NaverMovieRepository
) : ViewModel() {
    val searchQuery = MutableLiveData<String>()

    private val _movies = MutableLiveData<List<Item>>()
    val movies: LiveData<List<Item>> = _movies

    private val _error = MutableLiveData<SearchError>()
    val error: LiveData<SearchError> = _error

    private val _hideKeyboard = MutableLiveData<Unit>()
    val hideKeyboard: LiveData<Unit> = _hideKeyboard

    private val TAG = MainViewModel::class.java.simpleName

    init {
        _error.value = SearchError.INIT
    }

    fun searchMovies() {
        val searchValue = searchQuery.value
        if (searchValue.isNullOrBlank()) {
            _error.value = SearchError.QUERY_IS_NONE
        } else {
            naverMovieRepositoryImpl.searchMovies(
                query = searchValue,
                onSuccess = { response ->

                    if (response.total == 0) {
                        _movies.value = null
                        _error.value = SearchError.RESPONSE_IS_NONE
                    } else {
                        _movies.value = response.items
                        _error.value = SearchError.NONE_ERROR
                        _hideKeyboard.value = Unit
                    }
                },
                onError = { errorMessage ->
                    _error.value = SearchError.RESPONSE_ERROR
                    Log.d(TAG, errorMessage)
                },
                onFailure = { t ->
                    _error.value = SearchError.NETWORK_FAILURE
                    Log.d(TAG, t.toString())
                },
                onCached = { movies ->
                    if (!movies.isNullOrEmpty()) {
                        _movies.postValue(movies)
                        _error.postValue(SearchError.NONE_ERROR)
                        _hideKeyboard.postValue(Unit)
                    }
                })
        }
    }
}