package com.onit.googlearchitecturestudy

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NaverAPIService {

    @GET("/v1/search/movie.json")
   suspend fun getMovieList(
        @Header("X-Naver-Client-Id") Client_Id: String,
        @Header("X-Naver-Client-Secret") Client_Secret: String,
        @Query("query") keyword: String
    ): Response<Movies>
}