package com.example.architecturestudy.di

import com.example.architecturestudy.network.CoinApiService
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val API_URL = "https://api.upbit.com/"

val networkModule = module {
    single { (get() as Retrofit).create(CoinApiService::class.java) }

    single {
        Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(get())
            .build()
    }

    single { GsonConverterFactory.create() as Converter.Factory }

}