package com.example.mystudy.data

import com.example.mystudy.data.remote.UpbitDataSource
import com.example.mystudy.network.UpbitApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.NewThreadScheduler
import io.reactivex.schedulers.Schedulers

/**
 * 가져온 데이터를 가공하는 클래스
 **/

class UpbitRepository (
    private val api: UpbitApi
){
    fun getMarket(): Single<String> =
        UpbitDataSource.getMarketResponse()
            .map {
                it.joinToString(",") {
                    it.market
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.newThread())

    fun getTicker(marketList: String) =
        UpbitDataSource.getTickerResponse(marketList)
}
