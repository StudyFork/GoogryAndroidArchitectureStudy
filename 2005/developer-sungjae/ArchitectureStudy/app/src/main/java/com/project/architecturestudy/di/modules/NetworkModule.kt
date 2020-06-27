package com.project.architecturestudy.di.modules

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.project.architecturestudy.BuildConfig
import com.project.architecturestudy.components.Constants.NAVER_SEARCH_BASE_URL
import com.project.architecturestudy.components.Constants.cacheSize
import com.project.architecturestudy.components.NaverApiService
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.text.DateFormat


val networkModule = module {
    single { Cache(androidApplication().cacheDir, cacheSize) }

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
        val request =
            chain.request()
                .newBuilder()
                .addHeader("X-Naver-Client-Id", BuildConfig.NAVER_CLIENT_ID)
                .addHeader("X-Naver-Client-Secret", BuildConfig.NAVER_CLIENT_SECRET)
                .build()

        chain.proceed(request)
    }

    single<CallAdapter.Factory> {
        RxJava2CallAdapterFactory.create()
    }

    single<Converter.Factory> {
        val gsonBuilder = GsonBuilder()
            .setPrettyPrinting()
            .setVersion(1.0)
            .create()
        GsonConverterFactory.create(gsonBuilder)
    }

    factory<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(get<Interceptor>())
            .addInterceptor { get { parametersOf(it) } }
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .addConverterFactory(get())
            .addCallAdapterFactory(get())
            .baseUrl(NAVER_SEARCH_BASE_URL)
            .build()
    }

    single {
        get<Retrofit>().create(NaverApiService::class.java)
    }
}