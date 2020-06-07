package com.kyudong.remote.di

import com.kyudong.remote.BuildConfig
import com.kyudong.remote.network.NaverApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .addInterceptor(get<Interceptor>())
            .build()
    }

    single {
        HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
        }
    }

    single {
        Interceptor {
            val chainRequest = it.request()
            val request = chainRequest
                .newBuilder()
                .apply {
                    addHeader("X-Naver-Client-Id", BuildConfig.CLIENT_ID)
                    addHeader("X-Naver-Client-Secret", BuildConfig.CLIENT_SECRET)
                }.build()

            return@Interceptor it.proceed(request)
        }
    }

    single {
        GsonConverterFactory.create()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://openapi.naver.com/")
            .addConverterFactory(get<GsonConverterFactory>())
            .client(get<OkHttpClient>())
            .build()
    }

    single {
        get<Retrofit>().create(NaverApiService::class.java)
    }
}