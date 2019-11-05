package com.ironelder.androidarchitecture.data

import com.ironelder.androidarchitecture.BuildConfig
import com.ironelder.androidarchitecture.common.CLIENT_BASE_URL
import com.ironelder.androidarchitecture.common.CLIENT_ID
import com.ironelder.androidarchitecture.common.CLIENT_KEY
import com.ironelder.androidarchitecture.common.CLIENT_TIMEOUT
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitForNaver {

    val SEARCH_API: SearchForNaver by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(CLIENT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(mOkHttpClient)
            .build()
        return@lazy retrofit.create(SearchForNaver::class.java)
    }

    private val mOkHttpClient: OkHttpClient by lazy {
        val httpClient = OkHttpClient.Builder().apply {
            connectTimeout(CLIENT_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(CLIENT_TIMEOUT, TimeUnit.SECONDS)
            addNetworkInterceptor(getLogInterceptor())
            addInterceptor(getNaverInterceptor())
        }
        return@lazy httpClient.build()
    }

    private fun getLogInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
    }

    private fun getNaverInterceptor(): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .header("X-Naver-Client-Id", CLIENT_ID)
                .header("X-Naver-Client-Secret", CLIENT_KEY)
            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }
}