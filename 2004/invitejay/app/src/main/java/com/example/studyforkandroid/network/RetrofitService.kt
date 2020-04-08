package com.example.studyforkandroid.network

import com.example.studyforkandroid.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitService {

    private const val baseUrl: String = BuildConfig.BASE_URI

    fun okHttpClient(): OkHttpClient {

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val headerInterceptor = Interceptor {
            val request = it.request()
                .newBuilder()
                .addHeader("X-Naver-Client-Id", BuildConfig.CLIENT_ID)
                .addHeader("X-Naver-Client-Secret", BuildConfig.SECRET_KEY)
                .build()
            return@Interceptor it.proceed(request)
        }

        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    val instance: RetrofitAPI by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient())
            .build()

        retrofit.create(RetrofitAPI::class.java)
    }
}