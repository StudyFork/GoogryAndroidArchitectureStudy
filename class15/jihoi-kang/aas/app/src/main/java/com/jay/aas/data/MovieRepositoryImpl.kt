package com.jay.aas.data

import com.jay.aas.model.Movie

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource
) : MovieRepository {

    override suspend fun getMovies(query: String): List<Movie> =
        movieRemoteDataSource.getMovies(query)

}