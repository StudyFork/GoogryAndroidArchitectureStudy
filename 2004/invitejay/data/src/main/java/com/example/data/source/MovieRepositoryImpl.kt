package com.example.data.source

import com.example.data.model.Movie
import com.example.data.source.remote.MovieRemoteDataSource

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource
) : MovieRepository {

    override fun getRemoteMovieList(
        query: String,
        onSuccess: (List<Movie>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        movieRemoteDataSource.getMovieList(query, onSuccess, onFailure)
    }
}