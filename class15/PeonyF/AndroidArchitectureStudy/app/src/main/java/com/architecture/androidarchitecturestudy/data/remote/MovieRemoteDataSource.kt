package com.architecture.androidarchitecturestudy.data.remote

import com.architecture.androidarchitecturestudy.data.model.MovieResponse

interface MovieRemoteDataSource {
    fun getMovieData(
        keyword: String,
        display: Int,
        onSuccess: (MovieResponse) -> Unit,
        onFailure: (Throwable) -> Unit,
        onEmptyList: (String) -> Unit
    )
}