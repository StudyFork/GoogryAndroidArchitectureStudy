package com.mtjin.androidarchitecturestudy.data.source.local.movie_search

import com.mtjin.androidarchitecturestudy.data.Movie
import com.mtjin.androidarchitecturestudy.data.source.local.movie_search.MovieDao
import com.mtjin.androidarchitecturestudy.data.source.local.movie_search.MovieLocalDataSource

class MovieLocalDataSourceImpl(private val movieDao: MovieDao) :
    MovieLocalDataSource {
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