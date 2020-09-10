package com.hong.architecturestudy.data.network

import com.hong.architecturestudy.BuildConfig
import com.hong.architecturestudy.components.Constants.BASE_URL
import com.hong.architecturestudy.components.Constants.DEFAULT_TIME_OUT
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitCreator {
    private val retrofit: Retrofit
    private val okHttpClient: OkHttpClient.Builder

    init {

        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

        okHttpClient = OkHttpClient.Builder().apply {
            connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
            readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
            writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
            addInterceptor(httpLoggingInterceptor)
            addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("X-Naver-Client-Id", BuildConfig.NAVER_CLIENT_ID)
                    .addHeader("X-Naver-Client-Secret", BuildConfig.NAVER_CLIENT_SECRET)
                    .build()
                chain.proceed(request)
            }
        }

        retrofit = Retrofit.Builder()
            .client(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    val service: NaverMovieApi by lazy {
        retrofit.create(NaverMovieApi::class.java)
    }
}