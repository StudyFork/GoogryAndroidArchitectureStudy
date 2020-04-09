package com.example.studyforkandroid.network

import com.example.studyforkandroid.data.MovieRes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {

    @GET("search/movie")
    fun movieRequest(
        @Query("query") title: String
    ): Call<MovieRes>
}
