package com.project.architecturestudy.components

import com.project.architecturestudy.BuildConfig
import com.project.architecturestudy.components.Constants.DEFAULT_TIME_OUT
import com.project.architecturestudy.components.Constants.NAVER_SEARCH_BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object Retrofit {
    private val retrofit: Retrofit
    private val okHttpClient: OkHttpClient.Builder

    init {

        val interceptor = HttpLoggingInterceptor().apply {
            this.setLevel(HttpLoggingInterceptor.Level.HEADERS)
            this.setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        okHttpClient = OkHttpClient.Builder()
        okHttpClient.apply {

            this.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
            this.addInterceptor(interceptor)
            this.addInterceptor { chain ->
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
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(NAVER_SEARCH_BASE_URL)
            .build()
    }

    val service: NaverApiService by lazy {
        retrofit.create(NaverApiService::class.java)
    }
}