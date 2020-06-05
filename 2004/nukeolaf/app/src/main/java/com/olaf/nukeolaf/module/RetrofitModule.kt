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
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<Interceptor>())
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single {
        Interceptor { chain: Interceptor.Chain ->
            val original = chain.request()
            chain.proceed(original.newBuilder().apply {
                addHeader("X-Naver-Client-Id", BuildConfig.NAVER_CLIENT_ID)
                addHeader(
                    "X-Naver-Client-Secret",
                    BuildConfig.NAVER_CLIENT_SECRET
                )
            }.build())
        }
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

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(
            RetrofitInterface::class.java
        )
    }
}