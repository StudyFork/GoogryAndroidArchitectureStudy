package com.example.mystudy.data.remote

import com.example.mystudy.network.MarketResponse
import com.example.mystudy.network.UpbitApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 서버에서 데이터를 가져오는 클래스
 **/

object UpbitDataSource {
     private val retrofit: UpbitApi = Retrofit.Builder()
        .baseUrl("https://api.upbit.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(UpbitApi::class.java)

    fun getMarketResponse(): Single<List<MarketResponse>> =
        retrofit.getMarkets()

    fun getTickerResponse(marketList: String) =
        retrofit.getTickers(marketList)
}
