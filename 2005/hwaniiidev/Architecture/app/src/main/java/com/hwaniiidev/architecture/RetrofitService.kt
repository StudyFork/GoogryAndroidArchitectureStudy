package com.hwaniiidev.architecture

import com.hwaniiidev.architecture.Model.ResponseMovieSearchData
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RetrofitService {


    @GET("v1/search/movie.json")
    fun getMovieSearchData(
        @Header("X-Naver-Client-Id") clientId : String,
        @Header("X-Naver-Client-Secret") clientPw : String,
        @Query("query") query: String
    ): retrofit2.Call<ResponseMovieSearchData>
}