package com.egiwon.data

import com.egiwon.data.model.ContentEntity
import io.reactivex.Single

interface NaverDataRepository {
    fun getContents(type: String, query: String): Single<ContentEntity>

    fun getContentsByHistory(type: String, query: String): Single<ContentEntity>

    fun getContentQueries(type: String): Single<List<String>>

    fun getCache(type: String): Single<ContentEntity>
}