package com.eunice.eunicehong.data.source

import com.eunice.eunicehong.data.source.remote.MovieRemoteDataSource

object MovieRepository {
    private val remoteDataSource = MovieRemoteDataSource()

    fun getMovieList(
        query: String,
        callback: MovieDataSource.LoadMoviesCallback
    ) {
        remoteDataSource.getMovieList(query, callback)
    }
}