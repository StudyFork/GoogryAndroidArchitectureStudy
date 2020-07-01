package com.example.data.module

import com.example.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val baseUrl = BuildConfig.BASE_URI

val retrofitModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single<OkHttpClient> {
        OkHttpClient.Builder().apply {
            addInterceptor(get())
            addInterceptor(get())
        }.build()
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
        Interceptor { chain ->
            chain.proceed(
                chain.request()
                    .newBuilder()
                    .addHeader("X-Naver-Client-Id", BuildConfig.CLIENT_ID)
                    .addHeader("X-Naver-Client-Secret", BuildConfig.SECRET_KEY)
                    .build()
            )
        }
    }
}
