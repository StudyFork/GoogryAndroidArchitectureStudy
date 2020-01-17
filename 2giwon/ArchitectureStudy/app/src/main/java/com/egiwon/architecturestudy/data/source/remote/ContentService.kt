package com.egiwon.architecturestudy.data.source.remote

import com.egiwon.architecturestudy.data.source.remote.response.ContentResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ContentService {
    @GET("v1/search/{type}.json")
    fun getContentInfo(
        @Path("type")
        type: String,
        @Query("query")
        query: String
    ): Single<ContentResponse>
}