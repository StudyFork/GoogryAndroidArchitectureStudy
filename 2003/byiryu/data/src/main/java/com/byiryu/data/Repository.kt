package com.byiryu.data

import com.byiryu.data.model.Item
import io.reactivex.Single

interface Repository {
    fun getMovieList(query: String) : Single<List<Item>>

    fun getMovieListWithRemote(query: String): Single<List<Item>>

    fun getPrevSearchQuery(): String

    fun isAutoLogin(): Boolean

    fun setAutoLogin()

    fun loginCheck(id: String?, pw: String?): Single<Boolean>
}

//