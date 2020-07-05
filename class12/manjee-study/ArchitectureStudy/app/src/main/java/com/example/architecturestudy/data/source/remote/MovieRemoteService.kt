package com.example.architecturestudy.data.source.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object MovieRemoteService {

    private const val naverApiUrl = "https://openapi.naver.com/v1/search/"
    private const val readWriteTimeOutSec: Long = 20

    val movieApiService: MovieRemoteDataSource
        get() = movieRetrofit.create(MovieRemoteDataSource::class.java)


    private val mHttpLoggingIntercepter = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val mOkHttpClient = OkHttpClient().newBuilder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(readWriteTimeOutSec, TimeUnit.SECONDS)
        .writeTimeout(readWriteTimeOutSec, TimeUnit.SECONDS)
        .addInterceptor(mHttpLoggingIntercepter)
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json;charset=UTF-8")
                .addHeader("X-Naver-Client-Id", "JSnG5WlnTivnbGj7NFqL")
                .addHeader("X-Naver-Client-Secret", "iDSdCN3VjL").build()
            chain.proceed(request)
        }

    private val movieRetrofit: Retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .baseUrl(naverApiUrl)
        .client(mOkHttpClient.build())
        .build()
}