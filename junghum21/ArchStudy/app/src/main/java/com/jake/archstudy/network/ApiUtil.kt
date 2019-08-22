package com.jake.archstudy.network

import com.jake.archstudy.network.service.UpbitService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiUtil {

    private const val BASE_URL = "https://api.upbit.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .run {
            val httpLoggingInterceptor =
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

            val client = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()

            baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

    private val upbitService = retrofit.create<UpbitService>()

    fun getUpbitService() = upbitService

}