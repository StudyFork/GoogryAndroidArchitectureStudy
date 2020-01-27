package com.egiwon.data

import com.egiwon.data.model.ContentEntity
import io.reactivex.Completable
import io.reactivex.Single

interface NaverLocalDataSource {
    fun getCacheContents(type: String): Single<ContentEntity>

    fun getContentQueries(type: String): Single<List<String>>

    fun getLocalContents(type: String, query: String): Single<ContentEntity>

    fun saveContents(type: String, query: String, response: ContentEntity): Completable
}