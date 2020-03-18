package com.mtjin.androidarchitecturestudy.data.search.source.local

import com.mtjin.androidarchitecturestudy.data.search.Movie

class MovieLocalDataSourceImpl(private val movieDao: MovieDao) : MovieLocalDataSource {
    override fun insertMovies(movies: List<Movie>) {
        movieDao.insertMovies(movies)
    }

    override fun getAllMovies(): List<Movie> {
        return movieDao.getAllMovies()
    }

    override fun getSearchMovies(title: String): List<Movie> {
        return movieDao.getMoviesByTitle(title)
    }

    override fun deleteAllMovies() {
        movieDao.deleteAllMovies()
    }
}