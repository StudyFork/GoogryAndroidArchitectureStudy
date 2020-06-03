package com.example.studyforkandroid.data.source

import com.example.studyforkandroid.data.Movie
import com.example.studyforkandroid.data.source.remote.MovieRemoteDataSourceImpl

class MovieRepositoryImpl(
    private val movieRemoteDataSourceImpl: MovieRemoteDataSourceImpl
) : MovieRepository {

    override fun getRemoteMovieList(
        query: String,
        onSuccess: (List<Movie>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        movieRemoteDataSourceImpl.getMovieList(query, onSuccess, onFailure)
    }
}