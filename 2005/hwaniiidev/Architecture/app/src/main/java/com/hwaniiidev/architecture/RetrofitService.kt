package com.hwaniiidev.architecture

import com.hwaniiidev.architecture.model.ResponseMovieSearchData
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
    ): retrofit2.Call<ResponseMovieSearchData>
}