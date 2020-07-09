package com.example.study.data.api

import com.example.study.data.model.NaverApiData
import com.example.study.view.baseUrl
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NaverApi {

    @GET("/v1/search/movie.json")
    fun getSearch(
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") clientPw: String,
        @Query("query") query: String
    ): Call<NaverApiData>

    object NaverRetrofit{
        private val retrofit: Retrofit = Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val SERVICE: NaverApi = retrofit.create(
            NaverApi::class.java)
    }

}



