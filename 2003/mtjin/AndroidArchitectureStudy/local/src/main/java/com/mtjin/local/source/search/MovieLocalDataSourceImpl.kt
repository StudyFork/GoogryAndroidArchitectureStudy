package com.mtjin.local.source.search

import com.mtjin.data.source.search.local.MovieLocalDataSource
import com.mtjin.local.mapper.mapperMovieListDataToLocal
import com.mtjin.local.mapper.mapperMovieListLocalToData
import com.mtjin.data.model.search.Movie as dataMovie

internal class MovieLocalDataSourceImpl(private val movieDao: MovieDao) :
    MovieLocalDataSource {
    override fun insertMovies(movies: List<dataMovie>) {
        movieDao.insertMovies(mapperMovieListDataToLocal(movies))
    }

    override fun getAllMovies(): List<dataMovie> {
        return mapperMovieListLocalToData(movieDao.getAllMovies())
    }

    override fun getSearchMovies(title: String): List<dataMovie> {
        return mapperMovieListLocalToData(movieDao.getMoviesByTitle(title))
    }

    override fun deleteAllMovies() {
        movieDao.deleteAllMovies()
    }
}