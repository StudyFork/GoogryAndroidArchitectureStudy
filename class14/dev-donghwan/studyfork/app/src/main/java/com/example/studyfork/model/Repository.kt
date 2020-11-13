package com.example.studyfork.model

import io.reactivex.disposables.Disposable

interface Repository {
    fun searchMovie(
        query: String,
        success: (MovieSearchResponse) -> Unit,
        fail: (Throwable) -> Unit,
    ): Disposable
}