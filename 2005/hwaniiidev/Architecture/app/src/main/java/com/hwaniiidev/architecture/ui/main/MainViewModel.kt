package com.hwaniiidev.architecture.ui.main

import android.util.Log
import androidx.databinding.ObservableField
import com.hwaniiidev.architecture.R
import com.hwaniiidev.architecture.data.repository.NaverMovieRepositoryImpl
import com.hwaniiidev.architecture.model.Item

class MainViewModel(
    val naverMovieRepositoryImpl: NaverMovieRepositoryImpl
) {
    val searchQuery = ObservableField<String>()
    val movies = ObservableField<List<Item>>()
    val error = ObservableField<SearchError>()
    val hideKeyboard = ObservableField<Unit>()
    private val TAG = MainViewModel::class.java.simpleName

    init {
        error.set(SearchError.INIT)
    }

    fun searchMovies() {
        val searchValue = searchQuery.get()
        if (searchValue.isNullOrBlank()) {
            //TODO : 에러처리 "검색어 입력"
            error.set(SearchError.QUERY_IS_NONE)
        } else {
            naverMovieRepositoryImpl.searchMovies(
                query = searchValue,
                onSuccess = { response ->

                    if (response.total == 0) {
                        //TODO : 에러처리 showResponseIsNone
                        movies.set(null)
                        error.set(SearchError.RESPONSE_IS_NONE)
                    } else {
                        //TODO : 영화리스트 추가
                        movies.set(response.items)
                        error.set(SearchError.NONE_ERROR)
                        hideKeyboard.notifyChange()
                    }
                },
                onError = { errorMessage ->
                    //TODO : 에러처리 showResponseError
                    error.set(SearchError.RESPONSE_ERROR)
                    Log.d(TAG, errorMessage)
                },
                onFailure = { t ->
                    //TODO : 에러처리 showNetworkFailure
                    error.set(SearchError.NETWORK_FAILURE)
                    Log.d(TAG, t.toString())
                },
                onCached = { movies ->
                    if (!movies.isNullOrEmpty()) {
                        //TODO : 영화리스트 추가
                        this.movies.set(movies)
                        error.set(SearchError.NONE_ERROR)
                        hideKeyboard.notifyChange()
                        movies.forEach {
                            Log.d(TAG, it.title)
                        }
                    }
                })
        }
    }
}