package com.byiryu.local

import com.byiryu.data.model.Item
import com.byiryu.data.source.local.LocalDataSource
import com.byiryu.local.model.data.AppPreference
import com.byiryu.local.model.data.MovieDao
import com.byiryu.local.model.mapper.MovieMapper
import io.reactivex.Completable
import io.reactivex.Single

internal class LocalDataSourceImpl(
    private val movieDao: MovieDao,
    private val pref: AppPreference
) : LocalDataSource{

    override fun getAll(): Single<List<Item>> {
        return movieDao.getAll().map {
            MovieMapper.localToData(it)
        }
    }

    override fun saveMovies(movies: List<Item>): Completable {
        return movieDao.deleteAll()
            .andThen {
                movieDao.insertAll(MovieMapper.dataToLocal(movies))
            }
    }

    override fun isAutoLogin(): Boolean {
        return pref.isAutoLogin()
    }

    override fun setAutoLogin() {
        pref.setAutoLogin()
    }

    override fun setPrevQuery(query: String) {
        pref.setPrevQuery(query)
    }

    override fun getPrevSearchQuery(): String {
        return pref.getPrevQuery()
    }

}