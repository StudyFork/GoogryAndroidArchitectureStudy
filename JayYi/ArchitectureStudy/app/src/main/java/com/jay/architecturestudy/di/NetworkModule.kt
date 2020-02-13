package com.jay.architecturestudy.di

import android.util.Log
import com.jay.architecturestudy.network.Api
import com.jay.architecturestudy.network.NetworkException
import com.jay.architecturestudy.network.service.ApiService
import com.jay.architecturestudy.util.peekBody
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    factory { (chain: Interceptor.Chain) ->
        chain.proceed(
            chain.request()
                .newBuilder()
                .addHeader("X-Naver-Client-Id", CLIENT_ID)
                .addHeader("X-Naver-Client-Secret", CLIENT_SECRET)
                .build()
        ).also { response ->
            val statusCode = response.code()
            if (statusCode == 200) {
                Log.i("ApiClient", "res=${response}, body=${response.peekBody()}")
            }

            NetworkException.create(response)?.run { throw this }
        }
    }

    single<CallAdapter.Factory> {
        RxJava2CallAdapterFactory.create()
    }
    single<Converter.Factory> {
        GsonConverterFactory.create()
    }

    factory {
        OkHttpClient.Builder()
            .addInterceptor { get { parametersOf(it) } }
            .readTimeout(READ_TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .connectTimeout(CONNECTION_TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(baseApiUrl)
            .client(get<OkHttpClient>())
            .addCallAdapterFactory(get())
            .addConverterFactory(get())
            .build()

    }

    single {
        get<Retrofit>().create(ApiService::class.java)
    }

    single { Api(get()) }
}

private const val CLIENT_ID = "P8gzwMjtR8JUN2GPMjil"
private const val CLIENT_SECRET = "RUgmzQWH2g"

private const val READ_TIMEOUT_SECONDS = 30
private const val WRITE_TIMEOUT_SECONDS = 10
private const val CONNECTION_TIMEOUT_SECONDS = 30

private const val baseApiUrl = "https://openapi.naver.com"
