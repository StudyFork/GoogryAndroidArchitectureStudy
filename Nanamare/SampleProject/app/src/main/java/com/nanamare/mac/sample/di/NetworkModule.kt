package com.nanamare.mac.sample.di

import com.nanamare.mac.sample.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.GlobalContext.get
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.upbit.com"

val networkModule: Module = module {

    single {
        OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = when {
                BuildConfig.DEBUG -> HttpLoggingInterceptor.Level.BASIC
                else -> HttpLoggingInterceptor.Level.NONE
            }
        } as Interceptor).build()
    }

    single {
        RxJava2CallAdapterFactory.create() as CallAdapter.Factory
    }

    single {
        GsonConverterFactory.create() as Converter.Factory
    }

    single {
        Retrofit.Builder()
            .client(get())
            .addCallAdapterFactory(get())
            .addConverterFactory(get())
            .baseUrl(BASE_URL)
            .build()
    }

}