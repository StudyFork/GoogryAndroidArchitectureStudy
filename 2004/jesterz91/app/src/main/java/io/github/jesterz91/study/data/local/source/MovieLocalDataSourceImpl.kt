package io.github.jesterz91.study.data.local.source

import io.github.jesterz91.study.data.local.dao.MovieDao
import io.github.jesterz91.study.data.local.model.MovieLocal
import io.reactivex.Maybe

class MovieLocalDataSourceImpl(private val movieDao: MovieDao) : MovieLocalDataSource {

    override fun loadMovieInfo(query: String): Maybe<List<MovieLocal>> {
        return movieDao.loadMovieInfo(query)
    }

    override fun saveMovieInfo(movies: List<MovieLocal>) {
        movieDao.insertAll(movies)
    }
}