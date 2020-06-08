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
    private val TAG = MainViewModel::class.java.simpleName

    fun searchMovies(searchValue: String) {
        if (searchValue.isNullOrBlank()) {
            //TODO : 에러처리 "검색어 입력"
        }
        naverMovieRepositoryImpl.searchMovies(
            query = searchValue,
            onSuccess = { response ->
                //view.hideKeyBoard()

                if (response.total == 0) {
                    //TODO : 에러처리 showResponseIsNone
                } else {
                    //TODO : 영화리스트 추가
                    movies.set(response.items)
                }
            },
            onError = { errorMessage ->
                //TODO : 에러처리 showResponseError
                error.set(2)
                Log.d(TAG, errorMessage)
            },
            onFailure = { t ->
                //TODO : 에러처리 showNetworkFailure
                error.set(3)
                Log.d(TAG, t.toString())
            },
            onCached = { movies ->
                if (!movies.isNullOrEmpty()) {
                    //TODO : 영화리스트 추가
                    this.movies.set(movies)
                    movies.forEach{
                        Log.d(TAG,it.title)
                    }
                }
            })
    }
}