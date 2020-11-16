package com.architecture.androidarchitecturestudy.data.repository

import com.architecture.androidarchitecturestudy.data.model.MovieResponse
import com.architecture.androidarchitecturestudy.data.remote.MovieRemoteDataSource

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource
) : MovieRepository {
    override fun getMovieData(
        keyword: String,
        display: Int,
        onSuccess: (MovieResponse) -> Unit,
        onFailure: (Throwable) -> Unit,
        onEmptyList: (String) -> Unit
    ) {
        return movieRemoteDataSource.getMovieData(
            keyword = keyword,
            display = display,
            onSuccess = onSuccess,
            onFailure = onFailure,
            onEmptyList = onEmptyList
        )
    }
}

