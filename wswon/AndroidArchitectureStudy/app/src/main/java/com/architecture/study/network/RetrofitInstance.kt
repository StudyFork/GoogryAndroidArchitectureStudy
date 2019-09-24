package com.architecture.study.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {C

    inline fun <reified T> getInstance(url: String): T {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(T::class.java)
    }

}

