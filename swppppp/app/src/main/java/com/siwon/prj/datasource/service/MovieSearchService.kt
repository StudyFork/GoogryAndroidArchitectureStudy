package com.siwon.prj.datasource.service

import com.siwon.prj.model.ApiInfo
import com.siwon.prj.model.Movies
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MovieSearchService {
    @GET("/v1/search/movie.json")
    fun search(
        @Header("X-Naver-Client-Id") Client_Id: String,
        @Header("X-Naver-Client-Secret") Client_Secret: String,
        @Query("query") input: String
    ): Call<Movies>
}

//object MovieSearchServiceImpl{
//
//    private val retrofit = Retrofit.Builder()
//        .baseUrl(ApiInfo.BASE_URL)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    val service: MovieSearchService = retrofit.create(MovieSearchService::class.java)
//}