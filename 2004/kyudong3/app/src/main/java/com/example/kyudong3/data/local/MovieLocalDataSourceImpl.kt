package com.example.kyudong3.data.local

import com.example.kyudong3.data.model.Movie
import com.example.kyudong3.mapper.MovieLocalMapper

class MovieLocalDataSourceImpl(
    private val movieDao: MovieDao,
    private val movieLocalMapper: MovieLocalMapper
) : MovieLocalDataSource {
    override fun getMovieList(query: String): List<Movie> {
        return movieLocalMapper.toDomain(movieDao.fetchCacheMovieData(query))
    }

    override fun saveMovieList(movieList: List<Movie>) {
        movieDao.saveMovieData(movieLocalMapper.toData(movieList))
    }
}