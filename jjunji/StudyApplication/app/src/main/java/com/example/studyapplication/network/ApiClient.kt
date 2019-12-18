package com.example.studyapplication.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ApiClient {
    private const val BASE_URL = "https://openapi.naver.com/"
    private val retrofit by lazy { getClient() }

    fun getService(): Service {
        return retrofit!!.create(Service::class.java)
    }

    private fun getClient(): Retrofit? {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson()))
            .build()
    }

    private fun gson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    private fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .retryOnConnectionFailure(false)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(headerInterceptor())
            .addInterceptor(httpLoggingInterceptor())
            .build()
    }

    private fun headerInterceptor(): Interceptor {
        return object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val request = chain.request().newBuilder()
                    .addHeader("X-Naver-Client-Id", "eNLy_J4hwzGVWUIldEXT")
                    .addHeader("X-Naver-Client-Secret", "SONPvb_Alx")
                    .build()
                return chain.proceed(request)
            }
        }
    }

    private fun httpLoggingInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.HEADERS
        return interceptor
    }
}