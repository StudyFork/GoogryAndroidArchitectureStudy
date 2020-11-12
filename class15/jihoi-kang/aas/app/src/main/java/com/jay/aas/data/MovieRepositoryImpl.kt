package com.jay.aas.data

import com.jay.aas.model.Movie

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
) : MovieRepository {

    private val TAG = this::class.java.simpleName

    override suspend fun getSearchMovies(query: String): List<Movie> {
        val movies = movieRemoteDataSource.getSearchMovies(query)
        movieLocalDataSource.insertMovies(movies)

        return movies
    }

    override suspend fun getMovies(): List<Movie> =
        movieLocalDataSource.getMovies()
}