package com.byiryu.data.source.local

import com.byiryu.data.model.Item
import io.reactivex.Completable
import io.reactivex.Single

interface LocalDataSource {
    fun getAll(): Single<List<Item>>

    fun saveMovies(movies: List<Item>): Completable

    fun isAutoLogin(): Boolean

    fun setAutoLogin()

    fun setPrevQuery(query: String)

    fun getPrevSearchQuery(): String
}