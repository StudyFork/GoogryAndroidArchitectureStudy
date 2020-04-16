package com.eunice.eunicehong.data.repository

import com.eunice.eunicehong.data.model.Movie
import com.eunice.eunicehong.data.remote.MovieRemoteDataSource

object MovieRepository {
    private val remoteDataSource = MovieRemoteDataSource()

    fun getMovieList(
        query: String,
        onSuccess: (movieList: List<Movie>) -> Unit,
        onFailure: (e: Throwable) -> Unit
    ) {
        remoteDataSource.getMovieList(query, onSuccess, onFailure)
    }
}