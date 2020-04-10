package com.olaf.nukeolaf

import com.olaf.nukeolaf.BuildConfig.NAVER_CLIENT_ID
import com.olaf.nukeolaf.BuildConfig.NAVER_CLIENT_SECRET
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val client: RetrofitInterface
    private const val BASE_URL = "https://openapi.naver.com/"


    init {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain: Interceptor.Chain ->
                val original = chain.request()
                chain.proceed(original.newBuilder().apply {
                    addHeader("X-Naver-Client-Id", NAVER_CLIENT_ID)
                    addHeader("X-Naver-Client-Secret", NAVER_CLIENT_SECRET)
                }.build())
            }
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        client = retrofit.create(
            RetrofitInterface::class.java
        )
    }
}