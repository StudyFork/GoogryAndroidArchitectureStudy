package com.example.mystudy.di

import com.example.mystudy.data.remote.UpbitService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

fun getRemoteServiceModules(url: String) = module {
    single { GsonConverterFactory.create() }
    single { RxJava2CallAdapterFactory.create() }
    single {
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(get<GsonConverterFactory>())
            .addCallAdapterFactory(get<RxJava2CallAdapterFactory>())
            .build()
    }
    single<UpbitService> { get<Retrofit>().create(UpbitService::class.java)}
}