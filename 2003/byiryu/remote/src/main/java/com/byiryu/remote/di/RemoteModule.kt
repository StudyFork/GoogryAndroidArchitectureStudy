package com.byiryu.remote.di

import com.byiryu.data.source.remote.RemoteDataSource
import com.byiryu.remote.RemoteDataSourceImpl
import com.byiryu.remote.model.apis.Apis
import com.byiryu.remote.conf.RemoteConf.BASE_URL
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val remoteModule = module {

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single { NaverHeaderInterceptor() }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<NaverHeaderInterceptor>())
            .addInterceptor(get<HttpLoggingInterceptor>())
            .readTimeout(20000, TimeUnit.MILLISECONDS)
            .connectTimeout(10000, TimeUnit.MILLISECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }

    single {
        GsonBuilder().serializeSpecialFloatingPointValues()
            .serializeNulls()
            .create()
    }

    single {
        RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
    }

    single {
        GsonConverterFactory.create(get())
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(get<GsonConverterFactory>())
            .addCallAdapterFactory(get<RxJava2CallAdapterFactory>())
            .client(get())
            .build()
    }

    single {
        get<Retrofit>().create(Apis::class.java)
    }

    single<RemoteDataSource> {
        RemoteDataSourceImpl(get())
    }

}