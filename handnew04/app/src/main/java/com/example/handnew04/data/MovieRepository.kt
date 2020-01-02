package com.example.handnew04.data

import com.example.handnew04.data.local.MovieLocalDataSource
import com.example.handnew04.data.local.MovieLocalDataSourceImpl
import com.example.handnew04.data.remote.MovieRemoteDataSource
import com.example.handnew04.data.remote.MovieRemoteDataSourceImpl

class MovieRepository {
    private var movieLocalDataSource: MovieLocalDataSource = MovieLocalDataSourceImpl()
    private var movieRemoteDataSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl()

    fun getMovieData(query: String, success: (NaverMovieResponse) -> Unit, fail: (Throwable) -> Unit) =
        movieRemoteDataSource.getMovieData(query, success, fail)
}