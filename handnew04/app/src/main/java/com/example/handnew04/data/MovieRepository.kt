package com.example.handnew04.data

import com.example.handnew04.data.local.MovieLocalDataSource
import com.example.handnew04.data.remote.MovieRemoteDataSource

class MovieRepository(
    val movieLocalDataSource: MovieLocalDataSource,
    val movieRemoteDataSource: MovieRemoteDataSource
) {
    fun getMovieData(
        query: String,
        success: (NaverMovieResponse) -> Unit,
        fail: (Throwable) -> Unit
    ) =
        movieRemoteDataSource.getMovieData(query, success, fail)
}