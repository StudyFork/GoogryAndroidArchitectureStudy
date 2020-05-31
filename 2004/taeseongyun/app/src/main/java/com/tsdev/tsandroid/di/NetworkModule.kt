package com.tsdev.tsandroid.di

import com.tsdev.tsandroid.BuildConfig
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/***
 * single :
 * factory :
 * viewModel :
 ***/

private const val X_NAVER_CLIENT_ID = "X-Naver-Client-Id"
private const val X_NAVER_CLIENT_SECRET = "X-Naver-Client-Secret"
private const val REQUEST_TIME_OUT = 1000L

val networkModule = module {
    single {
        Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single {
        OkHttpClient.Builder().addInterceptor { get() }.addInterceptor { chain ->
            chain.proceed(chain.request().newBuilder().also {
                it.addHeader(X_NAVER_CLIENT_ID, BuildConfig.Client_ID)
                it.addHeader(X_NAVER_CLIENT_SECRET, BuildConfig.Client_SECRET)
            }.build())
        }
            .callTimeout(REQUEST_TIME_OUT, TimeUnit.MILLISECONDS)
            .readTimeout(REQUEST_TIME_OUT, TimeUnit.MILLISECONDS)
            .writeTimeout(REQUEST_TIME_OUT, TimeUnit.MILLISECONDS)
    }

    single {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }
}