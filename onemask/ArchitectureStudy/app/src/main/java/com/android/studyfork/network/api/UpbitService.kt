package com.android.studyfork.network.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

class UpbitService private constructor(){
    val upbitApi: UpbitApi

    init {
        val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            Timber.d(message)
        })
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().apply {
            addInterceptor(interceptor)
            connectTimeout(15, TimeUnit.SECONDS)
            readTimeout(20, TimeUnit.SECONDS)
            writeTimeout(15, TimeUnit.SECONDS)
        }.build()


        upbitApi = Retrofit.Builder()
            .baseUrl("https://api.upbit.com/v1/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(UpbitApi::class.java)
    }

    companion object {
        @Volatile
        private var INSTANCE: UpbitService? = null
        fun getInstance(): UpbitService {
            return INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: UpbitService().also { INSTANCE = it }
            }
        }
    }

}
