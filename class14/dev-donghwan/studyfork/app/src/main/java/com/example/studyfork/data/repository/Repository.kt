package com.example.studyfork.data.repository

import com.example.studyfork.data.model.MovieSearchResponse
import io.reactivex.disposables.Disposable

interface Repository {
    fun searchMovie(
        query: String,
        success: (MovieSearchResponse) -> Unit,
        fail: (Throwable) -> Unit,
    ): Disposable

    fun putRecentSearchList(item: String)

    fun getRecentSearchList(): List<String>

}