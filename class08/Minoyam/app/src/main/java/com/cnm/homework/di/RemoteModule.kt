package com.cnm.homework.di

import com.cnm.homework.BuildConfig
import com.cnm.homework.data.source.remote.NaverQueryRemoteDataSource
import com.cnm.homework.data.source.remote.NaverQueryRemoteDataSourceImpl
import com.cnm.homework.data.source.remote.network.NaverApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val remoteModule = module {
    factory { createOkHttpClient() }
    factory { createNaverApi(get()) }
    single { crearteRetrofit(get()) }
    single<NaverQueryRemoteDataSource> {
        NaverQueryRemoteDataSourceImpl(get())
    }
}

private fun createNaverApi(retrofit: Retrofit): NaverApi {
    return retrofit.create(NaverApi::class.java)
}

private fun crearteRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://openapi.naver.com/")
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        })
        .addInterceptor {
            val request = it.request()
                .newBuilder()
                .addHeader("X-Naver-Client-Id", "D9_uu75G1uJiKwd4bNrb")
                .addHeader("X-Naver-Client-Secret", "eBuWaNbn92")
                .build()
            it.proceed(request)
        }
        .build()
}

