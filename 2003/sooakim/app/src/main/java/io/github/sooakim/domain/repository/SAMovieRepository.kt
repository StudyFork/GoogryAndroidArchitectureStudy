package io.github.sooakim.domain.repository

import io.github.sooakim.domain.model.SAMovieModel
import io.reactivex.Flowable

interface SAMovieRepository : SARepository {
    val latestMovieQuery: String

    fun getMovies(query: String): Flowable<List<SAMovieModel>>
}