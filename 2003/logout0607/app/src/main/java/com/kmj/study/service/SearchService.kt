package com.kmj.study.service

import com.kmj.study.dto.MovieResponseDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface SearchService {

    @Headers("Content-Type: application/json")
    @GET("/v1/search/movie.json")
    fun getSearchItems(
        @Header("X-Naver-Client-Id") clientId: String? = "K3ohseHxNBpDJCj8GbiW",
        @Header("X-Naver-Client-Secret") clientSecret: String? = "oXywdQU9E1",
        @Header("User-Agent") userAgent: String? = "curl/7.49.1",
        @Query("display") display: String? = "20",
        @Query("start") start: String? = "1",
        @Query("query") query: String
    ): Call<MovieResponseDto>
}