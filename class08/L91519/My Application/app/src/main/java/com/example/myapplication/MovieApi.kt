package com.example.myapplication

import com.example.myapplication.model.MovieResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("search/movie.json")
    fun searchMovie(@Query("query") query: String): Call<MovieResult>
}