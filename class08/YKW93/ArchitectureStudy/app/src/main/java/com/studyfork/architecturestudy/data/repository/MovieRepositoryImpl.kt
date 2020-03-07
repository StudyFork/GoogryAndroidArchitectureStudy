package com.studyfork.architecturestudy.data.repository

import com.studyfork.architecturestudy.data.model.MovieResponse
import com.studyfork.architecturestudy.data.source.remote.MovieRemoteDataSource
import io.reactivex.disposables.Disposable

class MovieRepositoryImpl(private val movieRemoteDataSource: MovieRemoteDataSource) : MovieRepository {

    override fun getMovieList(
        query: String,
        loading: (Boolean) -> Unit,
        success: (MovieResponse) -> Unit,
        error: (Throwable) -> Unit
    ): Disposable {
        return movieRemoteDataSource.getMovieList(query, loading, success, error)
    }
}