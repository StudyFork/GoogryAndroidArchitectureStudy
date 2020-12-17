package com.example.androidarchitecturestudy.data.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitClient @Inject constructor() {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val headerInterceptor = Interceptor {
        val request = it.request()
            .newBuilder()
            .addHeader(
                "X-Naver-Client-Id",
                CLIENT_ID
            )
            .addHeader(
                "X-Naver-Client-Secret",
                CLIENT_SECRET
            )
            .build()
        return@Interceptor it.proceed(request)

    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(headerInterceptor)
        .build()

    fun <T> naverMovieInterface(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }

    companion object {
        private const val BASE_URL = "https://openapi.naver.com/"
        private const val CLIENT_ID = "zoFgl2SLHGQXO2WoadKj"
        private const val CLIENT_SECRET = "Tx2eCHuLZ6"
    }
}