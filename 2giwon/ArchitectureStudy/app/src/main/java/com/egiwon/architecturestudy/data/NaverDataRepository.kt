package com.egiwon.architecturestudy.data

import com.egiwon.architecturestudy.data.source.remote.response.ContentResponse
import io.reactivex.Single

interface NaverDataRepository {
    fun getContents(type: String, query: String): Single<ContentResponse>

    fun getContentsByHistory(type: String, query: String): Single<ContentResponse>

    fun getContentQuerys(type: String): Single<List<String>>

    fun getCache(type: String): Single<ContentResponse>
}