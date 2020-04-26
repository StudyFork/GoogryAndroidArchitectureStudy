package com.mtjin.data.source.search

import com.mtjin.data.model.search.Movie

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