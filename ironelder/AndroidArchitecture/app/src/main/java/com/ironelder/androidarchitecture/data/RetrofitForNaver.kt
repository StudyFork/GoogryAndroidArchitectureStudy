package com.ironelder.androidarchitecture.data

import com.ironelder.androidarchitecture.common.CLIENT_BASE_URL
import com.ironelder.androidarchitecture.common.CLIENT_ID
import com.ironelder.androidarchitecture.common.CLIENT_KEY
import com.ironelder.androidarchitecture.common.CLIENT_TIMEOUT
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitForNaver {

    private val mOkHttpClient:OkHttpClient
        get() {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("X-Naver-Client-Id", CLIENT_ID)
                    .header("X-Naver-Client-Secret", CLIENT_KEY)
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            httpClient.connectTimeout(CLIENT_TIMEOUT, TimeUnit.SECONDS)
            httpClient.readTimeout(CLIENT_TIMEOUT, TimeUnit.SECONDS)
            httpClient.addNetworkInterceptor(logging)
            return httpClient.build()
        }

    private val mRetrofit:Retrofit
        get() {
            return Retrofit.Builder()
                .baseUrl(CLIENT_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(mOkHttpClient)
                .build()
        }

    fun getSearchForNaver(): ISearchForNaver = mRetrofit.create(ISearchForNaver::class.java)
}