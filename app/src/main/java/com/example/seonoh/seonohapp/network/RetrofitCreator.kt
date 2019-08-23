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
class RetrofitCreator {

    companion object{
        fun createNet() : Api{
            val okClient = OkHttpClient.Builder()
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
            client.addInterceptor(
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
           val okClient = client.build()


           val retrofit =  Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()


           return retrofit.create(Api::class.java)
        }

    }

}
