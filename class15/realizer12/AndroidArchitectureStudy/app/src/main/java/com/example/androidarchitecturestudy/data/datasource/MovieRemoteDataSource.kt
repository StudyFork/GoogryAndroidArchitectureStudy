package com.example.androidarchitecturestudy.data.datasource

import com.example.androidarchitecturestudy.data.GetMovieInfo

interface MovieRemoteDataSource {

    fun getMovieSearchResult(
        movieName: String,
        onSuccess: (GetMovieInfo.MovieList) -> Unit,
        onFailure: (Throwable) -> Unit
    )
}