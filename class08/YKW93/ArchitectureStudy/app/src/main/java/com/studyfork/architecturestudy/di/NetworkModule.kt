package com.studyfork.architecturestudy.di

import com.studyfork.architecturestudy.BuildConfig
import com.studyfork.architecturestudy.network.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    factory { provideOkHttpClient() }
    factory { provideApi(get()) }
    single { provideRetrofit(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl("https://openapi.naver.com/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor {
            val request = it.request()
                .newBuilder()
                .addHeader("X-Naver-Client-Id", "ZYEN_CRH91MJAxUlbYKP")
                .addHeader("X-Naver-Client-Secret", "rrMJFQ1Miu")
                .build()
            it.proceed(request)
        }
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        })
        .build()
}

fun provideApi(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}