package com.nanamare.mac.sample.api

import com.nanamare.mac.sample.AppApplication
import com.nanamare.mac.sample.BuildConfig
import com.nanamare.mac.sample.R
import com.nanamare.mac.sample.api.upbit.UpBitService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ServiceDefine {

    private val BASE_URL = AppApplication.instance.context().getString(R.string.upbit_base_url)

    val retrofit : Retrofit = Retrofit.Builder()
        .client(OkHttpClient.Builder()
            .addInterceptor( HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BASIC
                else
                    HttpLoggingInterceptor.Level.NONE
            } as Interceptor)
            .build())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()


}