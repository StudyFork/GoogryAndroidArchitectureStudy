package org.study.kotlin.androidarchitecturestudy.api.retorifit

import org.study.kotlin.androidarchitecturestudy.api.UpbitApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

open class RetrofitBuilder {
    private val baseUrl = "https://api.upbit.com/"
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    val service = retrofit.create(UpbitApi::class.java)
}