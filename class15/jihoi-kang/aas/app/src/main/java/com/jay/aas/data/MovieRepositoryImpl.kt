package com.jay.aas.data

import com.jay.aas.model.Movie
import com.jay.aas.model.SearchHistory

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
) : MovieRepository {

    private val TAG = this::class.java.simpleName

    override suspend fun getSearchMovies(query: String): List<Movie> {
        val movies = movieRemoteDataSource.getSearchMovies(query)
        movieLocalDataSource.insertMovies(query, movies)

        return movies
    }

    override suspend fun getMovies(): List<Movie> =
        movieLocalDataSource.getMovies()

    override fun getSearchHistories(): List<SearchHistory> =
        movieLocalDataSource.getSearchHistories()

}