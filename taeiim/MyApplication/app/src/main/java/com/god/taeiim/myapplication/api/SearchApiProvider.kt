package com.god.taeiim.myapplication.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

fun provideAuthApi(): SearchApi = Retrofit.Builder()
    .baseUrl("https://openapi.naver.com/v1/")
    .client(provideOkHttpClient(provideLoggingInterceptor(), AuthInterceptor()))
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(SearchApi::class.java)


private fun provideOkHttpClient(
    interceptor: HttpLoggingInterceptor,
    authInterceptor: AuthInterceptor?
): OkHttpClient = OkHttpClient.Builder()
    .run {
        if (null != authInterceptor) {
            addInterceptor(authInterceptor)
        }
        addInterceptor(interceptor)
        build()
    }

private fun provideLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

internal class AuthInterceptor() : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain)
            : Response = with(chain) {
        val newRequest = request().newBuilder().run {
            addHeader("X-Naver-Client-Id", "4kOUBLrom4188o7GQtsO")
            addHeader("X-Naver-Client-Secret", "bRjUd3_TJB")
            build()
        }
        proceed(newRequest)
    }
}