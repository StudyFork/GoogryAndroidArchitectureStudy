package com.hwaniiidev.data

import com.hwaniiidev.data.model.ResponseMovieSearchData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RetrofitService {
    @GET("v1/search/movie.json")
    fun getMovieSearchData(
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") clientPw: String,
        @Query("query") query: String,
        @Query("display") display: Int
    ): Call<ResponseMovieSearchData>
}