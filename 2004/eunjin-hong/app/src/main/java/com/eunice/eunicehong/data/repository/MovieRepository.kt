package com.eunice.eunicehong.data.repository

import com.eunice.eunicehong.data.remote.MovieRemoteDataSource
import com.eunice.eunicehong.data.source.MovieDataSource

object MovieRepository {
    private val remoteDataSource = MovieRemoteDataSource()

    fun getMovieList(
        query: String,
        callback: MovieDataSource.LoadMoviesCallback
    ) {
        remoteDataSource.getMovieList(query, callback)
    }
}