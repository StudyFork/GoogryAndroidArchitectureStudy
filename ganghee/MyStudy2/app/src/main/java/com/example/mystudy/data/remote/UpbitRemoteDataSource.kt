package com.example.mystudy.data.remote

import com.example.mystudy.data.MarketResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

/**
 * 서버에서 데이터를 가져오는 클래스
 **/

class UpbitRemoteDataSource(private val upbitService: UpbitService) {

    fun getMarkets(): Single<List<MarketResponse>> =
        upbitService.getMarkets()
            .subscribeOn(Schedulers.io())

    fun getTickers(marketList: String) =
        upbitService.getTickers(marketList)
            .subscribeOn(Schedulers.io())
}
