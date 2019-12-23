package com.example.architecture_project.server

import com.example.architecture_project.data.NaverApi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface NaverService {

    @GET("/v1/search/movie.json")
    fun getMovie(
        @Header("X-Naver-Client-Id") Client_Id: String,
        @Header("X-Naver-Client-Secret") Client_Secret: String,
        @Query("query") keyword: String
    ): Call<NaverApi>
}