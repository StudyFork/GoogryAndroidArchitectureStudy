package com.jay.aas.data

import com.jay.aas.model.Movie

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
) : MovieRepository {

    private val TAG = this::class.java.simpleName

    override suspend fun getMovies(query: String): List<Movie> {
        var movies = movieLocalDataSource.getMovies(query)
        if (movies.isEmpty()) {
            movies = movieRemoteDataSource.getMovies(query)
            movies.forEach { it.query = query }
            movieLocalDataSource.insertMovies(movies)
        }

        return movies
    }

}