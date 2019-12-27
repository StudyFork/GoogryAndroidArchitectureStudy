package com.hansung.firstproject.network

import com.hansung.firstproject.data.MovieResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NaverApiService {
    @GET("/v1/search/movie.json")
    fun getRepoList(
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") clientSecret: String,
        @Query("query") query: String,
        @Query("display") display: Int
    ): Call<MovieResponseModel>
}
