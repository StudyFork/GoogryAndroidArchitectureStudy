package com.example.seonoh.seonohapp.di

import com.example.seonoh.seonohapp.BuildConfig
import com.example.seonoh.seonohapp.network.Api
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .setLevel(Level.BASIC)
                .log(Platform.INFO)
                .request("OH_req")
                .response("OH_res")
                .build()
        )
        .addNetworkInterceptor(StethoInterceptor())
        .build()

    @Provides
    @Singleton
    fun provideCoinApi(client: OkHttpClient): Api = Retrofit.Builder()
        .baseUrl("https://api.upbit.com/")
        .client(client)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Api::class.java)
}