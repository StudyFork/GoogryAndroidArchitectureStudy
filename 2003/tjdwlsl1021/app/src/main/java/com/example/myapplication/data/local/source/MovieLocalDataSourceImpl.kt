package com.example.myapplication.data.local.source

import com.example.myapplication.data.local.MovieDao
import com.example.myapplication.model.MovieEntity

class MovieLocalDataSourceImpl(private val movieDao: MovieDao) : MovieLocalDataSource {
    override suspend fun insertMovies(movies: List<MovieEntity>) {
        movieDao.insertMovies(movies)
    }

    override suspend fun getSearchMovies(title: String): List<MovieEntity> {
        return movieDao.getMoviesByTitle(title)
    }
}