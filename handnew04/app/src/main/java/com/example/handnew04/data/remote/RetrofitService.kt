package com.example.handnew04.data.remote

import com.example.handnew04.data.NaverMovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("/v1/search/movie.json")
    fun requestSearchMovie(
        @Query("query") inputText: String,
        @Query("display") limit: Int
    ): Call<NaverMovieResponse>

}