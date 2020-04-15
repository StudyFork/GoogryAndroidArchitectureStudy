package io.github.jesterz91.study.domain.repository

import io.github.jesterz91.study.domain.entity.Movie
import io.reactivex.Single

interface MovieRepository {

    fun getMovie(query: String): Single<List<Movie>>
}