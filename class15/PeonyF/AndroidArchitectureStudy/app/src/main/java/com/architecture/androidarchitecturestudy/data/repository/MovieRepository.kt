package com.architecture.androidarchitecturestudy.data.repository

import com.architecture.androidarchitecturestudy.data.model.MovieResponse

interface MovieRepository {
    fun getMovieData(
        keyword: String,
        display: Int,
        onSuccess: (MovieResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    )
}