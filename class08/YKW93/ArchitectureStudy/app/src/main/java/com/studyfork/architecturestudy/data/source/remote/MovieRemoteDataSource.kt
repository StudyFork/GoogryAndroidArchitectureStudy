package com.studyfork.architecturestudy.data.source.remote

import com.studyfork.architecturestudy.data.model.MovieResponse
import io.reactivex.disposables.Disposable

interface MovieRemoteDataSource {
    fun getMovieList(
        query: String,
        loading: (Boolean) -> Unit = {},
        success: (MovieResponse) -> Unit,
        error: (Throwable) -> Unit
    ): Disposable
}