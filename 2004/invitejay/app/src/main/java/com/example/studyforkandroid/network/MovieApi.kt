package com.example.studyforkandroid.network

import com.example.studyforkandroid.data.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("search/movie")
    fun movieRequest(
        @Query("query") title: String
    ): Call<MovieResponse>
}
