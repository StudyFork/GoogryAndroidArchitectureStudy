package com.example.study

import com.example.study.model.NaverSearch
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {
    @GET("v1/search/movie.json")
    fun getMovieList(
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") clientSecret: String,
        @Query("query") query: String
    ) : Call<NaverSearch>
}