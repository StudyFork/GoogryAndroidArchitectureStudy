package com.example.studyfork.model

interface Repository {
    fun searchMovie(
        query: String,
        success: (MovieSearchResponse) -> Unit,
        fail: (Throwable) -> Unit,
    )

    fun disposableClear()
}