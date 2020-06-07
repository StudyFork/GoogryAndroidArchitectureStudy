package com.tsdev.remote.di

import com.tsdev.remote.BuildConfig
import com.tsdev.remote.network.NaverMovieAPI
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


private const val X_NAVER_CLIENT_ID = "X-Naver-Client-Id"
private const val X_NAVER_CLIENT_SECRET = "X-Naver-Client-Secret"
private const val REQUEST_TIME_OUT = 1000L

val networkModule = module {
    single {
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(get())
            .build()
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<Interceptor>())
            .addInterceptor { chain ->
                chain.proceed(
                    chain.request().newBuilder()
                        .addHeader(X_NAVER_CLIENT_ID, BuildConfig.CLIENT_ID)
                        .addHeader(X_NAVER_CLIENT_SECRET, BuildConfig.CLIENT_SECRET)
                        .build()
                )
            }
            .writeTimeout(REQUEST_TIME_OUT, TimeUnit.MILLISECONDS)
            .readTimeout(REQUEST_TIME_OUT, TimeUnit.MILLISECONDS)
            .callTimeout(REQUEST_TIME_OUT, TimeUnit.MILLISECONDS)
            .build()
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

    single { get<Retrofit>().create(NaverMovieAPI::class.java) }
}