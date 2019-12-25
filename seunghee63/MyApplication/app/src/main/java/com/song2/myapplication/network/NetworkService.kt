package com.song2.myapplication.network

import com.song2.myapplication.data.GetMovieDataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NetworkService{

    // 영화 검색 API
    @GET("/v1/search/movie.json")
    fun getMovieSearch(
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") secret: String,
        @Query("query") query : String,
        @Query("display") display : Int
    ) : Call<GetMovieDataResponse>
}