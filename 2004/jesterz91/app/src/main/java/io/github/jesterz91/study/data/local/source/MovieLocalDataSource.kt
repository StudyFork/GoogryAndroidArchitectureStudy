package io.github.jesterz91.study.data.local.source

import io.github.jesterz91.study.data.local.model.MovieLocal
import io.reactivex.Maybe

interface MovieLocalDataSource {

    fun loadMovieInfo(query: String): Maybe<List<MovieLocal>>

    fun saveMovieInfo(movies: List<MovieLocal>)
}