package com.example.remote.source

import com.example.remote.model.Movies
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MovieApi {
    @GET("/v1/search/movie.json")
    fun getSearchItems(
        @Header("X-Naver-Client-Id") clientId: String? = "5wa1hBD6a5XVIVLBnAyF",
        @Header("X-Naver-Client-Secret") clientSecret: String? = "ZkPcwerkjH",
        @Query("display") display: String? = "10",
        @Query("start") start: String? = "1",
        @Query("query") query: String
    ): Single<Movies>
}