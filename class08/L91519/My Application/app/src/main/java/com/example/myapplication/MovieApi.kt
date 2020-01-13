package com.example.myapplication

import com.example.myapplication.model.MovieResult
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieApi {

    @Headers(
        "X-Naver-Client-Id: iUus1XfFW9mDHFp7chUz",
        "X-Naver-Client-Secret: rJ_ZOUhfVD"
    )
    @GET("search/movie.json")
    fun searchMovie(@Query("query") query: String): Call<MovieResult>
}