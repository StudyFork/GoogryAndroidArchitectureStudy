package com.example.archstudy.network

import com.example.archstudy.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {

    private val retrofit: Retrofit
    val apiService: NaverMovieService

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.API_URI)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(NaverMovieService::class.java)
    }

    companion object {

        private var INSTANCE : RetrofitHelper? = null

        fun getInstance() : RetrofitHelper = INSTANCE ?: RetrofitHelper()
    }
}
