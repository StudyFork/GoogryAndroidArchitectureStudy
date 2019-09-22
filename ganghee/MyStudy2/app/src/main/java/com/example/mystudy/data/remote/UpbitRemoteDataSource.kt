package com.example.mystudy.data.remote

import com.example.mystudy.data.MarketResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 서버에서 데이터를 가져오는 클래스
 **/

object UpbitRemoteDataSource : UpbitService {
    private val retrofit: UpbitService = Retrofit.Builder()
        .baseUrl("https://api.upbit.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(UpbitService::class.java)

    override fun getMarkets(): Single<List<MarketResponse>> =
        retrofit.getMarkets()
            .subscribeOn(Schedulers.io())

    override fun getTickers(marketList: String) =
        retrofit.getTickers(marketList)
            .subscribeOn(Schedulers.io())


}
