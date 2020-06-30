package com.hwaniiidev.data.source.remote

import com.hwaniiidev.data.model.ResponseMovieSearchData

interface NaverMovieRemoteDataSource {
    fun getRemoteMovies(
        query: String,
        onSuccess: (response: ResponseMovieSearchData) -> Unit,
        onError: (errorMessage: String) -> Unit,
        onFailure: (t: Throwable) -> Unit
    )
}