package com.hansung.firstproject.data.source.remote.api

import com.hansung.firstproject.data.MovieResponseModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NaverApiServiceImpl {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://openapi.naver.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val naverSearchService: NaverApiService = retrofit.create(NaverApiService::class.java)

    fun getResult(
        clientId: String,
        clientSecret: String,
        keyword: String,
        display: Int
    ): Call<MovieResponseModel> =
        naverSearchService.getRepoList(clientId, clientSecret, keyword, display)
}