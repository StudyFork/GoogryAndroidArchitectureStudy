package com.example.model.data

import com.example.model.data.local.MovieLocalDataSource
import com.example.model.data.remote.MovieRemoteDataSource

internal class MovieRepositoryImpl(
    val movieLocalDataSource: MovieLocalDataSource,
    val movieRemoteDataSource: MovieRemoteDataSource
) : MovieRepository {

    override fun getMovieData(
        query: String,
        success: (NaverMovieResponse) -> Unit,
        fail: (Throwable) -> Unit
    ) =
        movieRemoteDataSource.getMovieData(query, success, fail)
}