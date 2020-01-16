package com.egiwon.architecturestudy.di

import com.egiwon.architecturestudy.BuildConfig
import com.egiwon.architecturestudy.data.source.remote.ContentsService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory<Interceptor> {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }
    factory { (chain: Interceptor.Chain) ->
        chain.proceed(
            chain.request()
                .newBuilder()
                .addHeader("X-Naver-Client-Id", API_ID)
                .addHeader("X-Naver-Client-Secret", API_SECRET)
                .build()
        )
    }
    single<CallAdapter.Factory> {
        RxJava2CallAdapterFactory.create()
    }
    single<Converter.Factory> {
        GsonConverterFactory.create()
    }

    factory {
        OkHttpClient.Builder()
            .addInterceptor(get<Interceptor>())
            .addInterceptor { get { parametersOf(it) } }
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://openapi.naver.com/")
            .client(get<OkHttpClient>())
            .addCallAdapterFactory(get())
            .addConverterFactory(get())
            .build()

    }
    single {
        get<Retrofit>().create(ContentsService::class.java)
    }
}

private const val API_ID = "N6fr8OFPNCzX7SVctnkG"
private const val API_SECRET = "WHmQ8WtbYf"
