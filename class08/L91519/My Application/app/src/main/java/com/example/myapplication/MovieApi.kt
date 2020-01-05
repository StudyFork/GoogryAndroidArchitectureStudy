package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("search/{searchType}.json")
    fun searchMovie(@Path("searchType") searchType: String, @Query("query") query: String): Call<MovieResult>
}