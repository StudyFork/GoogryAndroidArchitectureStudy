package com.architecture.androidarchitecturestudy.webservice

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class NetworkModule @Inject constructor() {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideNetworkService(retrofit: Retrofit):
            NetworkService = retrofit.create(NetworkService::class.java)

    companion object {
        private const val BASE_URL = "https://openapi.naver.com/"
    }
}