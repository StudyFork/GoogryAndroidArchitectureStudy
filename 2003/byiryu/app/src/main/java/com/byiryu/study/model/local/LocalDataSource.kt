package com.byiryu.study.model.local

import com.byiryu.study.model.data.MovieItem
import com.byiryu.study.model.local.pref.AppPreference
import io.reactivex.Completable
import io.reactivex.Single

class LocalDataSource constructor(
    private val movieDao: MovieDao,
    private val pref: AppPreference
) {

    fun getAll(): Single<List<MovieItem>> {
        return movieDao.getAll()
    }

    fun saveMovies(movies: List<MovieItem>): Completable {
        return movieDao.deleteAll()
            .andThen {
                movieDao.insertAll(movies)
            }
    }


    fun isAutoLogin(): Boolean {
        return pref.isAutoLogin()
    }

    fun setAutoLogin() {
        pref.setAutoLogin()
    }

    fun setPrevQuery(query: String) {
        pref.setPrevQuery(query)
    }

    fun getPrevSearchQuery(): String {
        return pref.getPrevQuery()
    }

}