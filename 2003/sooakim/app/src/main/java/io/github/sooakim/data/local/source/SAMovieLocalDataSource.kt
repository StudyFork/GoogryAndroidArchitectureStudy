package io.github.sooakim.data.local.source

import io.github.sooakim.data.model.SAMovieEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface SAMovieLocalDataSource : SALocalDataSource {
    var latestMovieQuery: String

    fun getMovies(query: String): Flowable<List<SAMovieEntity>>

    fun saveMovies(movies: List<SAMovieEntity>): Completable
}