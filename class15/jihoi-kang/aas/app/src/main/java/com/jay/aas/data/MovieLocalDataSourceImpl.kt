package com.jay.aas.data

import com.jay.aas.model.Movie
import com.jay.aas.room.MovieDao

class MovieLocalDataSourceImpl(
    private val movieDao: MovieDao
) : MovieLocalDataSource {

    override fun insertMovies(movies: List<Movie>) {
        movieDao.insertMovies(movies)
    }

    override fun getMovies(query: String): List<Movie> = movieDao.getMovies(query)

    override fun clearMovies() {
        movieDao.clearMovies()
    }
}