package com.example.kangraemin.model.remote.datadao

import com.example.kangraemin.model.remote.datamodel.Movies
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MovieInterface {

    @GET("/v1/search/movie.json")
    fun getSearchItems(
        @Header("X-Naver-Client-Id") clientId: String? = "5wa1hBD6a5XVIVLBnAyF",
        @Header("X-Naver-Client-Secret") clientSecret: String? = "ZkPcwerkjH",
        @Query("display") display: String? = "10",
        @Query("start") start: String? = "1",
        @Query("query") query: String
    ): Flowable<Movies>
}