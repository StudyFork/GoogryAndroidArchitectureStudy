package com.jay.architecturestudy.network

import android.util.Log
import com.jay.architecturestudy.network.service.ApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.io.IOException
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

object ApiClient {
    private const val READ_TIMEOUT_SECONDS = 30
    private const val WRITE_TIMEOUT_SECONDS = 10
    private const val CONNECTION_TIMEOUT_SECONDS = 30

    private const val baseApiUrl = "https://openapi.naver.com"

    val apiService: ApiService = getRetrofit(baseApiUrl).create()

    private fun getRetrofit(baseUrl: String): Retrofit {
        val builder = OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .readTimeout(READ_TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .connectTimeout(CONNECTION_TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(builder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

internal class HeaderInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder().apply {
            addHeader("X-Naver-Client-Id", "P8gzwMjtR8JUN2GPMjil")
            addHeader("X-Naver-Client-Secret", "RUgmzQWH2g")
        }.build()

        val response = chain.proceed(request)
        val statusCode = response.code()
        if (statusCode == 200) {
            Log.i("ApiClient", "res=${response}, body=${getPeekBody(response.body())}")
        }

        return response
    }

    private fun getPeekBody(body: ResponseBody?): String {
        return body?.let {
            val source = it.source()
            source.request(Long.MAX_VALUE)
            val buffer = source.buffer()
            buffer.clone().readString(Charset.forName("UTF-8"))
        } ?: ""
    }
}
