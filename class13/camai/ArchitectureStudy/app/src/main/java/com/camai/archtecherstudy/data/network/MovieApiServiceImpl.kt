package com.camai.archtecherstudy.data.network

import android.util.Log
import com.camai.archtecherstudy.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object MovieApiServiceImpl {

    private const val BASE_URL_NAVER_API = "https://openapi.naver.com/"
    private const val CLIENT_ID = "IgChPWP2oMYZzitYv0rW"
    private const val CLIENT_SECRET = "KKfBdZH0ZC"
    private const val CONNECT_TIMEOUT: Long = 15


    private val httpLoggingInterceptor = HttpLoggingInterceptor();

    fun create(): MovieApiService {
        val headerInterceptor = Interceptor {
            val request = it.request()
                .newBuilder()
                .addHeader("X-Naver-Client-Id", CLIENT_ID)
                .addHeader("X-Naver-Client-Secret", CLIENT_SECRET)
                .build()
            return@Interceptor it.proceed(request)

        }

        if(BuildConfig.DEBUG){
            Log.d("Movie", "true")
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
        else{
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }


        val client = OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .build()


        return Retrofit.Builder()
            .baseUrl(BASE_URL_NAVER_API)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApiService::class.java)

    }

}