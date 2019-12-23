package com.onit.googlearchitecturestudy

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NaverAPIService {

    @GET("/v1/search/movie.json")
    fun getMovieList(
        @Header("X-Naver-Client-Id") Client_Id: String,
        @Header("X-Naver-Client-Secret") Client_Secret: String,
        @Query("query") keyword: String
    ): Deferred<Movies>
}