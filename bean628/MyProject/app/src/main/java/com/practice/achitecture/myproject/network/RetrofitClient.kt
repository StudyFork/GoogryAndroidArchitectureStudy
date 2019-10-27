package com.practice.achitecture.myproject.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
    private val CONNECT_TIMEOUT: Long = 15
    private val BASE_URL: String = ""

    //log를 볼 수 있도록 httpLogginInterceptor를 만듦
    private val okHttpClient: OkHttpClient
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            val addHeaderInterceptor = Interceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .addHeader("", "")
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return OkHttpClient().newBuilder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(addHeaderInterceptor)
                .build()
        }

    //retrofit에 각종 값을 setting
    val retrofitInterface: RetrofitInterface
        get() {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient) // httpClient연결을 통해 log 확인
                .addConverterFactory(GsonConverterFactory.create()) //Gson을 쓸 수 있도록 Factory생성
                .build().create(RetrofitInterface::class.java)
        }
}
