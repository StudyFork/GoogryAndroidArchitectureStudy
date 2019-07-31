package org.study.kotlin.androidarchitecturestudy.di

import org.koin.dsl.module
import org.study.kotlin.androidarchitecturestudy.api.UpbitApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val tickerBaseUrl = "https://api.upbit.com/"
val retrofitMoudle = module {
    single {
        Retrofit.Builder()
            .baseUrl(tickerBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(UpbitApi::class.java)
    }
}