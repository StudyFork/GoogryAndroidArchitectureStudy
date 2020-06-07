package com.kyudong.local.db

import com.kyudong.data.local.MovieLocalDataSource
import com.kyudong.data.model.Movie
import com.kyudong.local.mapper.MovieLocalMapper

internal class MovieLocalDataSourceImpl(
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