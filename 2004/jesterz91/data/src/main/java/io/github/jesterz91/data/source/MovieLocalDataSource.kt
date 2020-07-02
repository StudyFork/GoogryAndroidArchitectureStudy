package io.github.jesterz91.data.source

import io.github.jesterz91.data.model.MovieEntity
import io.reactivex.Maybe

interface MovieLocalDataSource {

    fun loadMovieInfo(query: String): Maybe<List<MovieEntity>>

    fun saveMovieInfo(movies: List<MovieEntity>, query: String)
}