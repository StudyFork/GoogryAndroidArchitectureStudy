package com.mtjin.data.source.search.remote

import com.mtjin.data.model.search.Movie

interface MovieRemoteDataSource {
    fun getSearchMovies(
        query: String,
        start: Int = 1,
        success: (List<Movie>) -> Unit,
        fail: (Throwable) -> Unit
    )
}