package com.example.seonoh.seonohapp.network

import com.example.seonoh.seonohapp.BuildConfig
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.upbit.com/"

object RetrofitCreator {

    fun createClient() : OkHttpClient{
        val client = OkHttpClient.Builder()
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
        return client
    }

    val coinApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(createClient())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Api::class.java)


}