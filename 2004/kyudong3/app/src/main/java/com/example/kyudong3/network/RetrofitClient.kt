package com.example.kyudong3.network

import com.example.kyudong3.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://openapi.naver.com/"

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(naverClientHeaderInterceptor)
            .build()
    }

    private val httpLoggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        .apply {
            level =
                if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
        }

    private val naverClientHeaderInterceptor = Interceptor {
        val chainRequest = it.request()
        val request = chainRequest
            .newBuilder()
            .apply {
                addHeader("X-Naver-Client-Id", BuildConfig.CLIENT_ID)
                addHeader("X-Naver-Client-Secret", BuildConfig.CLIENT_SECRET)
            }.build()

        return@Interceptor it.proceed(request)
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    val naverService: NaverApiService by lazy {
        retrofit.create(NaverApiService::class.java)
    }
}