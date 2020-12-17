package com.example.androidarchitecturestudy.data.api

import com.example.androidarchitecturestudy.data.model.MovieData
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverMovieInterface {
    @GET("/v1/search/movie.json")
    fun searchMovies(
        @Query("query") query: String,
    ): Call<MovieData>

}