package com.mtjin.androidarchitecturestudy.data.source

import com.mtjin.androidarchitecturestudy.data.Movie

interface MovieRepository {
    fun getSearchMovies(
        query: String,
        success: (List<Movie>) -> Unit,
        fail: (Throwable) -> Unit
    )

    fun getPagingMovies(
        query: String,
        start: Int,
        success: (List<Movie>) -> Unit,
        fail: (Throwable) -> Unit
    )
}