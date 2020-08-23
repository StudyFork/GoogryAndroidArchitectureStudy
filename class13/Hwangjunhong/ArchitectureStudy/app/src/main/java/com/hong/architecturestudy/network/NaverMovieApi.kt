package com.hong.architecturestudy.network

import com.hong.architecturestudy.data.MovieResultData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NaverMovieApi {

    @GET("v1/search/movie.json")
    fun getMovies(
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") clientPw: String,
        @Query("query") query: String
    ): Call<MovieResultData>

}