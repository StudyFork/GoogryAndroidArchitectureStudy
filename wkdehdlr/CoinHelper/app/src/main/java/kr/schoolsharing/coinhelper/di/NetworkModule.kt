package kr.schoolsharing.coinhelper.di

import kr.schoolsharing.coinhelper.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun getNetworkModule(baseUrl: String) = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            }).build()
    }


    single {
        GsonConverterFactory.create() as Converter.Factory
    }
    single {
        Retrofit.Builder()
            .client(get())
            .addConverterFactory(get())
            .baseUrl(baseUrl)
            .build()
    }
}