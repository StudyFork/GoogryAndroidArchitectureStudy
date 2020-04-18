package io.github.jesterz91.study.domain.repository

import io.github.jesterz91.study.domain.entity.Movie
import io.reactivex.Flowable

interface MovieRepository {

    fun getMovieInfo(query: String): Flowable<List<Movie>>
}