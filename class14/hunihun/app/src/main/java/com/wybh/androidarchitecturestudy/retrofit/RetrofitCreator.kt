package com.wybh.androidarchitecturestudy.retrofit

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@InstallIn(ApplicationComponent::class)
@Module
class RetrofitCreator @Inject constructor() {

    private fun defaultRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(createOkHttpClient())
            .build()
    }

    fun <T> create(service: Class<T>): T {
        return defaultRetrofit().create(service)
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(RetrofitInterceptor())
            .build()
    }

    companion object {
        private const val API_BASE_URL = "https://openapi.naver.com/"
    }
}