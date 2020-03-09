package io.github.sooakim.data.local.source

import io.github.sooakim.data.local.dao.SAMovieDao
import io.github.sooakim.data.local.mapper.SAMovieLocalMapper
import io.github.sooakim.data.local.pref.SAPreferencesHelper
import io.github.sooakim.data.model.SAMovieData
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class SAMovieLocalDataSourceImpl(
    private val pref: SAPreferencesHelper,
    private val movieDao: SAMovieDao,
    private val movieLocalMapper: SAMovieLocalMapper
) : SAMovieLocalDataSource {
    override var latestMovieQuery: String
        get() = pref.latestMovieQuery
        set(value) {
            pref.latestMovieQuery = value
        }

    override fun getMovies(): Single<List<SAMovieData>> {
        return movieDao.getMovies()
            .map { it.map(movieLocalMapper::mapToData) }
            .subscribeOn(Schedulers.io())
    }

    override fun saveMovies(movies: List<SAMovieData>): Completable {
        return movieDao.deleteAll()
            .andThen(Single.just(movies))
            .map { it.map(movieLocalMapper::mapToLocal) }
            .flatMapCompletable(movieDao::insertMovies)
            .subscribeOn(Schedulers.io())
    }
}