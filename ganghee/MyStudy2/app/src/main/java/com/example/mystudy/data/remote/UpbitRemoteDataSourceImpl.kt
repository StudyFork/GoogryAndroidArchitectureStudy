package com.example.mystudy.data.remote

import com.example.mystudy.network.MarketResponse
import com.example.mystudy.network.UpbitRemoteDataSource
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 서버에서 데이터를 가져오는 클래스
 **/

object UpbitRemoteDataSourceImpl : UpbitRemoteDataSource {
    private val RETROFIT: UpbitRemoteDataSource = Retrofit.Builder()
        .baseUrl("https://api.upbit.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(UpbitRemoteDataSource::class.java)

    override fun getMarkets(): Single<List<MarketResponse>> =
        RETROFIT.getMarkets()
            .subscribeOn(Schedulers.io())

    override fun getTickers(marketList: String) =
        RETROFIT.getTickers(marketList)
            .subscribeOn(Schedulers.io())


}
