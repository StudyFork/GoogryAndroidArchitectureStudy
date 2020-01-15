package com.studyfork.architecturestudy.data.repository

import com.studyfork.architecturestudy.data.model.MovieResponse
import com.studyfork.architecturestudy.data.source.remote.MovieRemoteDataSourceImpl
import io.reactivex.disposables.Disposable

class MovieRepositoryImpl(private val movieRemoteDataSourceImpl: MovieRemoteDataSourceImpl) : MovieRepository {

    override fun getMovieList(
        query: String,
        loading: (Boolean) -> Unit,
        success: (MovieResponse) -> Unit,
        error: (Throwable) -> Unit
    ): Disposable {
        return movieRemoteDataSourceImpl.getMovieList(query, loading, success, error)
    }
}