package io.github.jesterz91.domain.repository

import io.github.jesterz91.domain.model.Movie
import io.reactivex.Flowable

interface MovieRepository {

    fun getMovieInfo(query: String): Flowable<List<Movie>>
}