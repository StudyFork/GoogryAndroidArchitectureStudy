package com.example.androidarchitecturestudy.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient(url:String) {
    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    var apiService:ApiServices=retrofit.create(ApiServices::class.java)
}