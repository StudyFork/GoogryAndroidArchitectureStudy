package com.example.androidarchitecturestudy.data.repository

import com.example.androidarchitecturestudy.data.GetMovieInfo

interface MovieRepository {
    fun getMovieSearchResult(
        movieName: String,
        onSuccess: (GetMovieInfo.MovieList) -> Unit,
        onFailure: (Throwable) -> Unit
    )
}