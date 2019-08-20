package com.test.androidarchitecture.network

import okhttp3.OkHttpClient
import okhttp3.internal.Internal.instance
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {

    private lateinit var retrofit: Retrofit

    fun getClient(): Retrofit {
        val builder = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .addHeader("Content-Type", "application/json")
                .method(original.method(), original.body())
                .build()
            chain.proceed(request)
        }
        builder.addInterceptor(interceptor)
        val okHttpClient = builder.connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS).build()
        retrofit = Retrofit.Builder()
            .baseUrl("https://api.upbit.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
        return retrofit
    }

    companion object {

        @Volatile
        private var instance: RetrofitClient? = null

        @JvmStatic
        fun getInstance(): RetrofitClient =
            instance ?: synchronized(this) {
                instance ?: RetrofitClient().also {
                    instance = it
                }
            }
    }

}