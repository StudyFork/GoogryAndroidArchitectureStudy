package io.github.sooakim.data.local.source

import io.github.sooakim.data.model.SAMovieData
import io.reactivex.Completable
import io.reactivex.Single

interface SAMovieLocalDataSource : SALocalDataSource {
    var latestMovieQuery: String

    fun getMovies(): Single<List<SAMovieData>>

    fun saveMovies(movies: List<SAMovieData>): Completable
}