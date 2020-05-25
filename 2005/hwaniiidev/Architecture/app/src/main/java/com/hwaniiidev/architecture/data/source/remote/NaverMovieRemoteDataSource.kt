package com.hwaniiidev.architecture.data.source.remote

import com.hwaniiidev.architecture.model.ResponseMovieSearchData

interface NaverMovieRemoteDataSource {
    fun getRemoteMovies(
        query: String,
        onSuccess: (response: ResponseMovieSearchData) -> Unit,
        onError: (errorMessage: String) -> Unit,
        onFailure: (t: Throwable) -> Unit
    )

}