package com.lllccww.studyforkproject


import com.lllccww.studyforkproject.Constants.API_SERVER_URI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SearchRetrofit {

    fun getService(): ApiService = retrofit.create(ApiService::class.java)

    private val retrofit =
        Retrofit.Builder()
            .baseUrl(API_SERVER_URI) // 도메인 주소
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}