package com.example.architecturestudy.di

import com.example.architecturestudy.network.CoinApiService
import com.example.architecturestudy.network.RetrofitHelper
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(RetrofitHelper.API_URL)
            .addConverterFactory(get())
            .build()
    }

    single { GsonConverterFactory.create() }

    single { (get() as Retrofit).create(CoinApiService::class.java) }
}