package io.github.jesterz91.remote.di

import io.github.jesterz91.data.source.MovieRemoteDataSource
import io.github.jesterz91.remote.BuildConfig
import io.github.jesterz91.remote.api.MovieApi
import io.github.jesterz91.remote.interceptor.MovieInterceptor
import io.github.jesterz91.remote.source.MovieRemoteDataSourceImpl
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

val remoteModule = module {

    single<MovieRemoteDataSource> { MovieRemoteDataSourceImpl(get()) }

    single { get<Retrofit>().create(MovieApi::class.java) }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get())
            .addConverterFactory(get())
            .addCallAdapterFactory(get())
            .build()
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<Interceptor>(named("movie")))
            .addInterceptor(get<Interceptor>(named("logging")))
            .build()
    }

    single<Converter.Factory> { MoshiConverterFactory.create() }

    single<CallAdapter.Factory> { RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()) }

    single<Interceptor>(named("movie")) { MovieInterceptor() }

    single<Interceptor>(named("logging")) {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }
}