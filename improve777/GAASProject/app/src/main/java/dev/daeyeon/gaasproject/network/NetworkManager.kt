package dev.daesin.gaasdy.network

import dev.daeyeon.gaasproject.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {
    var instance: UpbitApi? = null
        get() {
            if (field == null) {
                field = Retrofit.Builder()
                        .baseUrl("https://api.upbit.com/v1/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(OkHttpClient.Builder()
                                .addInterceptor(HttpLoggingInterceptor().apply {
                                    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                                    else HttpLoggingInterceptor.Level.NONE
                                })
                                .build())
                        .build()
                        .create(UpbitApi::class.java)
            }
            return field
        }
}