package com.example.kotlinapplication.network

import io.reactivex.plugins.RxJavaPlugins
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class SearchRetrofit{
    companion object{
       /* val clientId :String = "o4YDMUoQQ3B5C1aJtX2g"
        val clientScret :String = "FrW3bUTwCa"*/
        val API_BASE_URL = "https://openapi.naver.com/v1/search/"

        private fun defaultRetrofit() :Retrofit{
            return Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
    }
}