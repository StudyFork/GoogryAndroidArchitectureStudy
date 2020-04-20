package com.mtjin.data.search.source.remote

import com.mtjin.data.search.model.Movie

interface MovieRemoteDataSource {
    fun getSearchMovies(
        query: String,
        start: Int = 1,
        success: (List<Movie>) -> Unit,
        fail: (Throwable) -> Unit
    )
}