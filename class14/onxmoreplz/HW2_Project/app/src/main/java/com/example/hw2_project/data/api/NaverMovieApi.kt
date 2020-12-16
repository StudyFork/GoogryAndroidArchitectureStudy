package com.example.hw2_project.data.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverMovieApi {

    @GET("v1/search//movie.json")
    fun searchMovieInApi(
        @Query("query") query: String
    ): Call<NaverMovieData.NaverMovieResponse>

}