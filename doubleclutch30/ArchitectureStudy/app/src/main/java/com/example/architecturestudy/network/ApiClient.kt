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

    private const val ALL_TIMEOUT = 10L
    private const val BASE_URL = "https://openapi.naver.com"
    private val retrofit: Retrofit = createRetrofit(BASE_URL)

    val api : Api = retrofit.create(Api::class.java)

    private fun createRetrofit(url : String) : Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(url)
            client(createOkHttpClient())
            addConverterFactory(GsonConverterFactory.create())

        }.build()
    }

    private fun createOkHttpClient() : OkHttpClient {
        return OkHttpClient().newBuilder().apply {
            addInterceptor(createHttpLoggingInterceptor())
            addInterceptor(HeaderSettingInterceptor())
            connectTimeout(ALL_TIMEOUT, TimeUnit.SECONDS)

        }.build()
    }

    private fun createHttpLoggingInterceptor() : Interceptor {
        return  HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }


    private class HeaderSettingInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val chainRequest = chain.request()
            val request = chainRequest.newBuilder().apply {
                addHeader("X-Naver-Client-Id", "tr6M7jBKez2OeO2BOXSg")
                addHeader("X-Naver-Client-Secret", "S_DUMEv030")
            }.build()

            val response = chain.proceed(request)
            Log.i("ApiClient", "res=${response}")

            return response
        }
    }

    internal fun <T> getRetrofitService(restClass: Class<T>): T {
        return retrofit.create(restClass)
    }
}