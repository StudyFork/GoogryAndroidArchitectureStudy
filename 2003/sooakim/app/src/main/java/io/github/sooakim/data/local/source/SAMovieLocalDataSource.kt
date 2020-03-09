package io.github.sooakim.data.local.source

import io.github.sooakim.data.model.SAMovieEntity
import io.reactivex.Completable
import io.reactivex.Single

interface SAMovieLocalDataSource : SALocalDataSource {
    var latestMovieQuery: String

    fun getMovies(): Single<List<SAMovieEntity>>

    fun saveMovies(movies: List<SAMovieEntity>): Completable
}