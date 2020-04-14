package io.github.sooakim.di

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.github.sooakim.BuildConfig
import io.github.sooakim.data.remote.api.SAAuthApi
import io.github.sooakim.data.remote.api.SAAuthApiImpl
import io.github.sooakim.data.remote.api.SANaverMovieApi
import io.github.sooakim.data.remote.interceptor.SANaverAuthInterceptor
import io.github.sooakim.data.remote.source.SAAuthRemoteDataSource
import io.github.sooakim.data.remote.source.SAAuthRemoteDataSourceImpl
import io.github.sooakim.data.remote.source.SAMovieRemoteDataSource
import io.github.sooakim.data.remote.source.SAMovieRemoteDataSourceImpl
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val GSON_DATE_FORMAT = "E, dd MMM yyyy HH:mm:ss Z"

val remoteModule = module {
    single { SANaverAuthInterceptor() }

    single { HttpLoggingInterceptor() }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<SANaverAuthInterceptor>())
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single {
        GsonBuilder()
            .setDateFormat(GSON_DATE_FORMAT)
            .setPrettyPrinting()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    single { RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()) }

    single { GsonConverterFactory.create(get()) }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.NAVER_BASE_URL)
            .client(get())
            .addCallAdapterFactory(get<RxJava2CallAdapterFactory>())
            .addConverterFactory(get<GsonConverterFactory>())
            .build()
    }

    single { get<Retrofit>().create(SANaverMovieApi::class.java) }

    single<SAAuthApi> { SAAuthApiImpl() }

    single<SAAuthRemoteDataSource> { SAAuthRemoteDataSourceImpl(get()) }

    single<SAMovieRemoteDataSource> { SAMovieRemoteDataSourceImpl(get()) }
}