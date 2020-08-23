package com.example.myproject.retrofit.service

import com.example.myproject.R
import com.example.myproject.retrofit.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieService {
    @Headers(
        "X-Naver-Client-Id : ${R.string.naver_client_id} ",
        "X-Naver-Client-Secret : ${R.string.naver_client_secret}"
    )
    @GET("/movie.json")
    fun getMovies(
        @Query("query") title: String
    ): Call<Movie>

}