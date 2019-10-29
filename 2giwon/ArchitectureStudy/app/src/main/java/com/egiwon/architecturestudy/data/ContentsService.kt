package com.egiwon.architecturestudy.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ContentsService {
    @GET("search/{type}.json")
    fun getContentsInfo(
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") clientSecret: String,
        @Path("type") type: String,
        @Query("query") query: String
    ): Call<Content>
}