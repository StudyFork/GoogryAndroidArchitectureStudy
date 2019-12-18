package com.example.architecturestudy.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    private val ALL_TIMEOUT = 10L
    private val BASE_URL = "https://openapi.naver.com"
    private val okHttpClient : OkHttpClient
    private val retrofit: Retrofit


    private class HeaderSettingInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val chainRequest = chain.request()
            val request = chainRequest.newBuilder().apply {
                addHeader("X-Naver-Client-Id", "tr6M7jBKez2OeO2BOXSg")
                addHeader("X-Naver-Client-Secret", "S_DUMEv030")
            }.build()

            val response = chain.proceed(request)
            Log.i("ApiClient", "res=${response}")

            return chain.proceed(request)
        }

    }

    init {

        val httplogging = HttpLoggingInterceptor()
        httplogging.level = HttpLoggingInterceptor.Level.BODY

        okHttpClient = OkHttpClient().newBuilder().apply {
            addInterceptor(httplogging)
            addInterceptor(HeaderSettingInterceptor())
            connectTimeout(ALL_TIMEOUT, TimeUnit.SECONDS)

        }.build()


        retrofit = Retrofit.Builder().apply {
            baseUrl(BASE_URL)
            client(okHttpClient)
            addConverterFactory(GsonConverterFactory.create())

        }.build()

    }

    internal fun <T> getRetrofitService(restClass: Class<T>): T {
        return retrofit.create(restClass)
    }
}