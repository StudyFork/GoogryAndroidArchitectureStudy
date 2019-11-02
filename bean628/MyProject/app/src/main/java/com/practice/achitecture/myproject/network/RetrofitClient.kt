package com.practice.achitecture.myproject.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient(private val _baseUrl: String) {
    private val _connectionTime: Long = 15

    //log를 볼 수 있도록 httpLogginInterceptor를 만듦
    private fun makeOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        val addHeaderInterceptor = Interceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .addHeader("X-Naver-Client-Id", "pjKtnD_jiM0jPuKVf0ce")
                .addHeader("X-Naver-Client-Secret", "pZfWJLt0Hk")
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient().newBuilder()
            .connectTimeout(_connectionTime, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(addHeaderInterceptor)
            .build()
    }

    //retrofit에 각종 값을 setting
    val retrofitInterface: RetrofitService
        get() {
            return Retrofit.Builder()
                .baseUrl(_baseUrl)
                .client(makeOkHttpClient()) // httpClient연결을 통해 log 확인
                .addConverterFactory(GsonConverterFactory.create()) //Gson을 쓸 수 있도록 Factory생성
                .build().create(RetrofitService::class.java)
        }
}
