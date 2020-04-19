package io.github.sooakim.local

import io.github.sooakim.data.local.SAMovieLocalDataSource
import io.github.sooakim.data.model.SAMovieData
import io.github.sooakim.local.dao.SAMovieDao
import io.github.sooakim.local.mapper.SAMovieLocalMapper
import io.github.sooakim.local.pref.SAPreferencesHelper
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

internal class SAMovieLocalDataSourceImpl(
    private val pref: SAPreferencesHelper,
    private val movieDao: SAMovieDao
) : SAMovieLocalDataSource {
    override var latestMovieQuery: String
        get() = pref.latestMovieQuery
        set(value) {
            pref.latestMovieQuery = value
        }

    override fun getMovies(): Single<List<SAMovieData>> {
        return movieDao.getMovies()
            .map { it.map(SAMovieLocalMapper::mapToData) }
            .subscribeOn(Schedulers.io())
    }

    override fun saveMovies(movies: List<SAMovieData>): Completable {
        return movieDao.deleteAll()
            .andThen(Single.just(movies))
            .map { it.map(SAMovieLocalMapper::mapToLocal) }
            .flatMapCompletable(movieDao::insertMovies)
            .subscribeOn(Schedulers.io())
    }
}