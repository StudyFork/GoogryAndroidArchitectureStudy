package com.mtjin.androidarchitecturestudy.data.source.local

import com.mtjin.androidarchitecturestudy.data.Movie

class MovieLocalDataSourceImpl(private val movieDao: MovieDao) : MovieLocalDataSource {
    override suspend fun insertMovies(movies: List<Movie>) {
        movieDao.insertMovies(movies)
    }

    override suspend fun getAllMovies(): List<Movie> {
        return movieDao.getAllMovies()
    }

    override suspend fun getSearchMovies(title: String): List<Movie> {
        return movieDao.getMoviesByTitle(title)
    }

    override suspend fun deleteAllMovies() {
        movieDao.deleteAllMovies()
    }
}