package com.hyper.hyapplication.module

import com.hyper.hyapplication.source.NaverAPI
import com.hyper.hyapplication.source.remote.BASE_URL
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { getNaverRetrofit() }
}

fun getNaverRetrofit(): NaverAPI {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NaverAPI::class.java)
}