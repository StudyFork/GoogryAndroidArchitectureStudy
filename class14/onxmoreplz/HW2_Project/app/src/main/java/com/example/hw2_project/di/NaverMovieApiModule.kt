package com.example.hw2_project.di

import com.example.hw2_project.data.api.NaverMovieApi
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@Module
@InstallIn(ApplicationComponent::class)
class NaverMovieApiModule @Inject constructor() {
    fun create(): NaverMovieApi {

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val headerInterceptor = Interceptor {
            val request = it.request()
                .newBuilder()
                .addHeader("X-Naver-Client-Id", NAVER_CLIENT_ID)
                .addHeader("X-Naver-Client-Secret", NAVER_CLIENT_SECRET)
                .build()
            return@Interceptor it.proceed(request)
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(NAVER_MOVIE_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NaverMovieApi::class.java)
    }
    /*   @Provides
       fun provideBaseUrl() = NAVER_MOVIE_BASE_URL

       @Singleton
       @Provides
       fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
           val httpLoggingInterceptor = HttpLoggingInterceptor()
           httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

           val headerInterceptor = Interceptor {
               val request = it.request()
                   .newBuilder()
                   .addHeader("X-Naver-Client-Id", NAVER_CLIENT_ID)
                   .addHeader("X-Naver-Client-Secret", NAVER_CLIENT_SECRET)
                   .build()
               return@Interceptor it.proceed(request)
           }
           OkHttpClient.Builder()
               .addInterceptor(headerInterceptor)
               .addInterceptor(httpLoggingInterceptor)
               .build()
       } else {
           OkHttpClient
               .Builder()
               .build()
       }

       @Singleton
       @Provides
       fun provideRetrofit(
           okHttpClient: OkHttpClient,
           BASE_URL: String
       ): Retrofit = Retrofit.Builder()
           .addConverterFactory(GsonConverterFactory.create())
           .baseUrl(BASE_URL)
           .client(okHttpClient)
           .build()

       @Provides
       @Singleton
       fun provideNaverApi(retrofit: Retrofit) = retrofit.create(NaverMovieApi::class.java)*/

    companion object {
        private const val NAVER_MOVIE_BASE_URL = "https://openapi.naver.com"

        private const val NAVER_CLIENT_ID = "fQFY7M9rMOVD2KDT8Aaq"
        private const val NAVER_CLIENT_SECRET = "v8aD8p_Ri0"

    }
}
