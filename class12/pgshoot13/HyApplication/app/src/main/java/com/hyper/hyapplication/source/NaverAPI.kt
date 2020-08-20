package com.hyper.hyapplication.source

import com.hyper.hyapplication.model.ResultGetSearchMovie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NaverAPI {
    @GET("/v1/search/movie.json")
    fun getSearchMovie(
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") clientSecret: String,
        @Query("query") query: String,
        @Query("display") display: Int? = null,
        @Query("start") start: Int? = null
    ): Call<ResultGetSearchMovie>
}
