package com.studyfork.architecturestudy.data.repository

import com.studyfork.architecturestudy.data.model.MovieResponse
import io.reactivex.disposables.Disposable

interface MovieRepository {
    fun getMovieList(
        query: String,
        loading: (Boolean) -> Unit = {},
        success: (MovieResponse) -> Unit,
        error: (Throwable) -> Unit
    ): Disposable
}