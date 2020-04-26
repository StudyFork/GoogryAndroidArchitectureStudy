package com.mtjin.remote.di

import com.mtjin.remote.api.ApiClient
import com.mtjin.remote.api.ApiInterface
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule: Module = module {

    single<ApiInterface> { get<Retrofit>().create(ApiInterface::class.java) }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(ApiClient.BASE_URL)
            .client(get())
            .addConverterFactory(get<GsonConverterFactory>())
            .build()
    }

    single<GsonConverterFactory> { GsonConverterFactory.create() }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .run {
                addInterceptor(get<Interceptor>())
                build()
            }
    }

    single<Interceptor> {
        Interceptor { chain ->
            with(chain) {
                val newRequest = request().newBuilder()
                    .addHeader("X-Naver-Client-Id", "33chRuAiqlSn5hn8tIme")
                    .addHeader("X-Naver-Client-Secret", "fyfwt9PCUN")
                    .build()
                proceed(newRequest)
            }
        }
    }
}