package com.hwaniiidev.architecture.data.source.remote

import com.hwaniiidev.architecture.model.Item
import com.hwaniiidev.architecture.model.ResponseMovieSearchData

interface NaverMovieRemoteDataSource {
    fun getRemoteMovies(
        query: String,
        //onSuccess: (movies: ArrayList<Item>) -> Unit,
        onSuccess: (response: ResponseMovieSearchData) -> Unit,
        onError: (errorMessage: String) -> Unit,
        onFailure: (t: Throwable) -> Unit
    )

}