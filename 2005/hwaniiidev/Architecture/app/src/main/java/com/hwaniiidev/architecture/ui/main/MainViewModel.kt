package com.hwaniiidev.architecture.ui.main

import android.util.Log
import androidx.databinding.ObservableField
import com.hwaniiidev.architecture.data.repository.NaverMovieRepositoryImpl
import com.hwaniiidev.architecture.model.Item

class MainViewModel(
    val naverMovieRepositoryImpl: NaverMovieRepositoryImpl
) {
    val movies = ObservableField<List<Item>>()
    val error = ObservableField<Int>()
    val hideKeyboard = ObservableField<Unit>()
    private val TAG = MainViewModel::class.java.simpleName

    init {
        error.set(INIT)
    }

    fun searchMovies(searchValue: String) {
        if (searchValue.isNullOrBlank()) {
            //TODO : 에러처리 "검색어 입력"
            error.set(ERROR_QUERY_IS_NONE)
        }
        naverMovieRepositoryImpl.searchMovies(
            query = searchValue,
            onSuccess = { response ->

                if (response.total == 0) {
                    //TODO : 에러처리 showResponseIsNone
                    movies.set(null)
                    error.set(ERROR_RESPONSE_IS_NONE)
                } else {
                    //TODO : 영화리스트 추가
                    movies.set(response.items)
                    error.set(NONE_ERROR)
                    hideKeyboard.notifyChange()
                }
            },
            onError = { errorMessage ->
                //TODO : 에러처리 showResponseError
                error.set(ERROR_RESPONSE_ERROR)
                Log.d(TAG, errorMessage)
            },
            onFailure = { t ->
                //TODO : 에러처리 showNetworkFailure
                error.set(ERROR_NETWORK_FAILURE)
                Log.d(TAG, t.toString())
            },
            onCached = { movies ->
                if (!movies.isNullOrEmpty()) {
                    //TODO : 영화리스트 추가
                    this.movies.set(movies)
                    error.set(NONE_ERROR)
                    hideKeyboard.notifyChange()
                    movies.forEach {
                        Log.d(TAG, it.title)
                    }
                }
            })
    }

    companion object {
        const val INIT = 11
        const val NONE_ERROR = 10
        const val ERROR_RESPONSE_IS_NONE = 9
        const val ERROR_RESPONSE_ERROR = 8
        const val ERROR_NETWORK_FAILURE = 7
        const val ERROR_QUERY_IS_NONE = 6
    }
}