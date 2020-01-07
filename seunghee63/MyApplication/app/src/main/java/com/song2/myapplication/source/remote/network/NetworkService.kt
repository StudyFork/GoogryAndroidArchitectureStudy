package com.song2.myapplication.source.remote.network

import com.song2.myapplication.source.MovieDataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    // 영화 검색 API
    @GET("/v1/search/movie.json")
    fun getMovieSearch(
        @Query("query") query: String,
        @Query("display") display: Int
    ): Call<MovieDataResponse>
}