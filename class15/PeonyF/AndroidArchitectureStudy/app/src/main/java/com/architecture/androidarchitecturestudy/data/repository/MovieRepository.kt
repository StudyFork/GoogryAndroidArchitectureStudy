package com.architecture.androidarchitecturestudy.data.repository

import com.architecture.androidarchitecturestudy.data.model.MovieResponse

interface MovieRepository {
    fun getMovieData(
        keyword: String,
        cnt: Int,
        onSuccess: (result: MovieResponse) -> Unit,
        onFailure: (t: Throwable) -> Unit
    )
}