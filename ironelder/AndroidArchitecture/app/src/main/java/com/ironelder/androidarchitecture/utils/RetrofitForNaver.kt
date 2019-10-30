package com.ironelder.androidarchitecture.utils

import com.ironelder.androidarchitecture.common.CLIENT_BASE_URL
import com.ironelder.androidarchitecture.common.CLIENT_ID
import com.ironelder.androidarchitecture.common.CLIENT_KEY
import com.ironelder.androidarchitecture.common.CLIENT_TIMEOUT
import com.ironelder.androidarchitecture.data.TotalModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
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

    fun getSearchForNaver(): SearchForNaver = mRetrofit.create(SearchForNaver::class.java)

    interface SearchForNaver {
        @GET("v1/search/{type}.json")
        fun requestSearchForNaver(@Path("type") type: String, @Query("query") query: String = "Test"): Call<TotalModel>
    }
}