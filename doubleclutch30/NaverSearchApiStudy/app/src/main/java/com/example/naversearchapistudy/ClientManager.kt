package com.example.naversearchapistudy

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ClientManager {

    private val ALL_TIMEOUT = 10L
    private val BASE_URL = "https://openapi.naver.com/v1/search"
    private var okHttpClient : OkHttpClient
    private var retrofit: Retrofit

    private class HeaderSettingInterceptor : Interceptor {
         override fun intercept(chain: Interceptor.Chain): Response {
             TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

             val chainRequest = chain.request()
             val request = chainRequest.newBuilder().apply {
                 addHeader("X-Naver-Client_Id", "Xn4CxQ1rV4pc2_o1jkcx")
                 addHeader("X-Naver_Client_Secret", "7QzfBCfJm9")
             }.build()

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