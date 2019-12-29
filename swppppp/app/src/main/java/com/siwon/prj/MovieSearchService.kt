package com.siwon.prj

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MovieSearchService {
    @GET("/v1/search/movie.json")
    fun search(
        @Header("X-Naver-Client-Id") Client_Id: String,
        @Header("X-Naver-Client-Secret") Client_Secret: String,
        @Query("query") input: String
    ): Call<Movies>
}