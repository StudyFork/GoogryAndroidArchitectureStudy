package com.example.myapplication.data.local.source

import com.example.myapplication.data.local.MovieDao
import com.example.myapplication.data.local.MovieEntity

class MovieLocalDataSourceImpl(private val movieDao: MovieDao) : MovieLocalDataSource {
    override fun insertMovies(movies: List<MovieEntity>) {
        movieDao.insertMovies(movies)
    }

    override fun getSearchMovies(title: String): List<MovieEntity> {
        return movieDao.getMoviesByTitle(title)
    }
}