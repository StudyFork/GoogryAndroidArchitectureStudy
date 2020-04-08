package com.tsdev.tsandroid

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val X_NAVER_CLIENT_ID = "X-Naver-Client-Id"
private const val X_NAVER_CLIENT_SECRET = "X-Naver-Client-Secret"
private const val REQUEST_TIME_OUT = 100L

inline fun <reified T> creatorRetrofit(baseUrl: String): T {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(baseUrl)
        .client(okHttpClient())
        .build()
        .create(T::class.java)
}


fun okHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().addInterceptor(
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUGING)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        })
        .addInterceptor { chain ->
            val originRequest = chain.request()
            chain.proceed(originRequest.newBuilder().apply {
                addHeader(X_NAVER_CLIENT_ID, BuildConfig.Client_ID)
                addHeader(X_NAVER_CLIENT_SECRET, BuildConfig.Client_SECRET)
            }.build())
        }
        .writeTimeout(REQUEST_TIME_OUT, TimeUnit.MILLISECONDS)
        .readTimeout(REQUEST_TIME_OUT, TimeUnit.MILLISECONDS)
        .connectTimeout(REQUEST_TIME_OUT, TimeUnit.MILLISECONDS)
        .build()
}