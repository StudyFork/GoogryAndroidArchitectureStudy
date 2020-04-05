package com.olaf.nukeolaf

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.QueryMap

interface RetrofitInterface {

    @GET("movie.json")
    fun searchMovie(
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") clientSecret: String,
        @QueryMap map: Map<String, Any>
    )

}