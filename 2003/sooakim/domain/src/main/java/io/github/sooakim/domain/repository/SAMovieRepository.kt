package io.github.sooakim.domain.repository

import io.reactivex.Flowable

interface SAMovieRepository : SARepository {
    val latestMovieQuery: String

    fun getMovies(query: String): Flowable<List<io.github.sooakim.domain.model.SAMovieModel>>
}