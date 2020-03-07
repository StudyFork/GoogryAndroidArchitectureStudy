package com.example.kangraemin.model

import com.example.kangraemin.response.ItemsMovieSearch
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieSearchInterface {
    @GET("/v1/search/movie.json")
    fun getSearchItems(
        @Header("X-Naver-Client-Id") clientId: String? = "5wa1hBD6a5XVIVLBnAyF",
        @Header("X-Naver-Client-Secret") clientSecret: String? = "ZkPcwerkjH",
        @Query("display") display: String,
        @Query("start") start: String,
        @Query("query") query: String
    ): Flowable<ItemsMovieSearch>
}