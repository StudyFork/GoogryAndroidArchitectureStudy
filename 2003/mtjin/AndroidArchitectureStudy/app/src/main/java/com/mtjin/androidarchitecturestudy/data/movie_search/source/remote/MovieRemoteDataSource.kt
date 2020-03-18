package com.mtjin.androidarchitecturestudy.data.movie_search.source.remote

import com.mtjin.androidarchitecturestudy.data.movie_search.Movie

interface MovieRemoteDataSource {
    fun getSearchMovies(
        query: String,
        start: Int = 1,
        success: (List<Movie>) -> Unit,
        fail: (Throwable) -> Unit
    )
}