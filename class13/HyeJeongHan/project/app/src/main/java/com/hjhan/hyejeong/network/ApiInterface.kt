package com.hjhan.hyejeong.network

import com.hjhan.hyejeong.MovieData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface ApiInterface {
    @Headers(
        "X-Naver-Client-Id: ${Constants.NAVER_CLIENT_ID}",
        "X-Naver-Client-Secret: ${Constants.NAVER_CLIENT_SECRET}"
    )
    @GET("movie.json")
    fun getMovies(
        @Query("query") query: String?,
        @Query("display") displaySize: Int,
        @Query("start") startPosition: Int
    ): Call<MovieData>
}