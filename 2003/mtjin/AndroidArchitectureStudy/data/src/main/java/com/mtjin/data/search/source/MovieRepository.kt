package com.mtjin.data.search.source

import com.mtjin.data.search.model.Movie

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