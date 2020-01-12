package com.example.study.data.datasource.remote.network

import com.example.study.data.model.NaverSearch
import retrofit2.Call
import retrofit2.http.*

interface NaverApiService {
    @GET("v1/search/movie.json")
    fun getMovieList(
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") clientSecret: String,
        @Query("query") query: String
    ) : Call<NaverSearch>
}