package com.example.local.source

import com.example.data2.model.Movie
import com.example.data2.soruce.local.MovieLocalDataSource

import com.example.local.data.MovieDao
import com.example.local.mapper.MovieMapper


internal class MovieLocalDataSourceImpl(private val movieDao: MovieDao) : MovieLocalDataSource {
    override fun insertMovies(movies: List<Movie>) {
        movieDao.insertMovies(MovieMapper.dataToLocal(movies))
    }

    override fun getSearchMovies(title: String): List<Movie> {
        return MovieMapper.localToData(movieDao.getMoviesByTitle(title))
    }
}