package com.example.androidarchitecturestudy.Retrofit

import com.example.androidarchitecturestudy.BuildConfig
import com.example.androidarchitecturestudy.data.GetMovieInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface ApiServices {

    @Headers("X-Naver-Client-Id: ${BuildConfig.naver_clientId}", "X-Naver-Client-Secret: ${BuildConfig.naver_clientSecret}")
    @GET("movie.json")
    fun getMovieSearchResult(
        @Query("query") movieName: String
    ): Call<GetMovieInfo.MovieList>

}