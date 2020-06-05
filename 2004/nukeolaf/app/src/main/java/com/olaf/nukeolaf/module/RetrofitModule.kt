package com.olaf.nukeolaf.module

import com.olaf.nukeolaf.BuildConfig
import com.olaf.nukeolaf.network.RetrofitInterface
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://openapi.naver.com/"

val retrofitModule = module {
    single { okHttpClient() }
    single { retrofit(get()) }
    single { movieApi(get()) }
}

fun okHttpClient(): OkHttpClient = OkHttpClient.Builder()
    .addInterceptor { chain: Interceptor.Chain ->
        val original = chain.request()
        chain.proceed(original.newBuilder().apply {
            addHeader("X-Naver-Client-Id", BuildConfig.NAVER_CLIENT_ID)
            addHeader(
                "X-Naver-Client-Secret",
                BuildConfig.NAVER_CLIENT_SECRET
            )
        }.build())
    }
    .addInterceptor(HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }).build()

fun retrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

fun movieApi(retrofit: Retrofit): RetrofitInterface = retrofit.create(
    RetrofitInterface::class.java
)