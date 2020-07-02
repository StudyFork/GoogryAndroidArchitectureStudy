package io.github.jesterz91.local.source

import io.github.jesterz91.data.model.MovieEntity
import io.github.jesterz91.data.source.MovieLocalDataSource
import io.github.jesterz91.local.dao.MovieDao
import io.github.jesterz91.local.mapper.MovieLocalMapper
import io.reactivex.Maybe

internal class MovieLocalDataSourceImpl(private val movieDao: MovieDao) : MovieLocalDataSource {

    override fun loadMovieInfo(query: String): Maybe<List<MovieEntity>> {
        return movieDao
            .loadMovieInfo(query)
            .filter { it.isNotEmpty() }
            .map { it.map(MovieLocalMapper::mapToEntity) }
    }

    override fun saveMovieInfo(movies: List<MovieEntity>, query: String) {
        movies.map(MovieLocalMapper::mapToLocal)
            .map { it.copy(searchQuery = query) }
            .run(movieDao::insertAll)
    }
}