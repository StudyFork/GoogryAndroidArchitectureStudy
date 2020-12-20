package com.example.androidarchitecturestudy.data.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class RetrofitClient  {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val headerInterceptor = Interceptor {
            val request = it.request()
                .newBuilder()
                .addHeader(
                    "X-Naver-Client-Id",
                    CLIENT_ID
                )
                .addHeader(
                    "X-Naver-Client-Secret",
                    CLIENT_SECRET
                )
                .build()
            return@Interceptor it.proceed(request)

        }

        val client = OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    @Singleton
    fun naverMovieInterface(retrofit: Retrofit): NaverMovieInterface {
        return retrofit.create(NaverMovieInterface::class.java)
    }

    companion object {
        private const val BASE_URL = "https://openapi.naver.com/"
        private const val CLIENT_ID = "zoFgl2SLHGQXO2WoadKj"
        private const val CLIENT_SECRET = "Tx2eCHuLZ6"
    }
}