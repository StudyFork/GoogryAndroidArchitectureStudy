package com.buddman1208.architecturestudy.utils

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkServiceGenerator {

    var url: String = "https://openapi.naver.com/"

    private val serverBuilder = Retrofit.Builder().baseUrl(url).apply {

        val client = OkHttpClient.Builder().apply {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            addInterceptor(interceptor)

        }.build()

        client(client)

        addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        addConverterFactory(GsonConverterFactory.create())

    }.build()

    fun <S> createService(serviceClass: Class<S>): S {
        return serverBuilder.create(serviceClass)
    }

}