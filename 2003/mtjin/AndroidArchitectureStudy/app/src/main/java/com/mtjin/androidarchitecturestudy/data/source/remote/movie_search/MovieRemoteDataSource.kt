package com.mtjin.androidarchitecturestudy.data.source.remote.movie_search

import com.mtjin.androidarchitecturestudy.data.Movie

interface MovieRemoteDataSource {
    fun getSearchMovies(
        query: String,
        start: Int = 1,
        success: (List<Movie>) -> Unit,
        fail: (Throwable) -> Unit
    )
}