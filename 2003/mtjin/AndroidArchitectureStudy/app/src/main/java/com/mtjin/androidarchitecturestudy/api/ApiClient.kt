package com.mtjin.androidarchitecturestudy.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        private const val BASE_URL = "https://openapi.naver.com/"
        fun getApiClient(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(provideOkHttpClient(AppInterceptor()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun provideOkHttpClient(
            interceptor: AppInterceptor
        ): OkHttpClient
                = OkHttpClient.Builder()
            .run {
                addInterceptor(interceptor)
                build()
            }
    }
}