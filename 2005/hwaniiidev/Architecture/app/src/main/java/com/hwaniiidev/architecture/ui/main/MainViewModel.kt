package com.hwaniiidev.architecture.ui.main

import android.util.Log
import androidx.databinding.ObservableField
import com.hwaniiidev.architecture.data.repository.NaverMovieRepositoryImpl
import com.hwaniiidev.architecture.model.Item
import com.hwaniiidev.architecture.ui.main.MainActivity.Companion.ERROR_NETWORK_FAILURE
import com.hwaniiidev.architecture.ui.main.MainActivity.Companion.ERROR_QUERY_IS_NONE
import com.hwaniiidev.architecture.ui.main.MainActivity.Companion.ERROR_RESPONSE_ERROR
import com.hwaniiidev.architecture.ui.main.MainActivity.Companion.ERROR_RESPONSE_IS_NONE

class MainViewModel(
    val naverMovieRepositoryImpl: NaverMovieRepositoryImpl
) {
    val movies = ObservableField<List<Item>>()
    val error = ObservableField<Int>()
    val hideKeyboard = ObservableField<Unit>()
    private val TAG = MainViewModel::class.java.simpleName

    fun searchMovies(searchValue: String) {
        if (searchValue.isNullOrBlank()) {
            //TODO : 에러처리 "검색어 입력"
            error.set(ERROR_QUERY_IS_NONE)
        }
        naverMovieRepositoryImpl.searchMovies(
            query = searchValue,
            onSuccess = { response ->
                //view.hideKeyBoard()

                if (response.total == 0) {
                    //TODO : 에러처리 showResponseIsNone
                    movies.set(null)
                    error.set(ERROR_RESPONSE_IS_NONE)
                } else {
                    //TODO : 영화리스트 추가
                    movies.set(response.items)
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
                    hideKeyboard.notifyChange()
                    movies.forEach{
                        Log.d(TAG,it.title)
                    }
                }
            })
    }
}