package com.architecture.androidarchitecturestudy.webservice


import com.architecture.androidarchitecturestudy.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {
    private const val BASE_URL = "https://openapi.naver.com/"

    private val okHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor{
            val request = it.request()
                .newBuilder()
                .addHeader("X-Naver-Client-Id", BuildConfig.CLIENT_ID)
                .addHeader("X-Naver-Client-Secret", BuildConfig.CLIENT_KEY)
                .build()
            it.proceed(request)
        }.build()

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
    val NETWORK_SERVICE:NetworkService= retrofit.create(NetworkService::class.java)
}