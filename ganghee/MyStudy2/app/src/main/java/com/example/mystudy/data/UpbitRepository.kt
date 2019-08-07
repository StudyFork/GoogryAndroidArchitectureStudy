package com.example.mystudy.data

import com.example.mystudy.data.remote.UpbitRemoteDataSource
import io.reactivex.Single

/**
 * 가져온 데이터를 가공하는 클래스
 **/

class UpbitRepository {
    fun getMarket(): Single<String> =
        UpbitRemoteDataSource.getMarketResponse()
            .map {
                it.joinToString(",") {
                    it.market
                }
            }

    fun getTicker(marketList: String) =
        UpbitRemoteDataSource.getTickerResponse(marketList)

    companion object {
        private var INSTANCE: UpbitRepository? = null

        fun getInstance(): UpbitRepository {
            if (INSTANCE == null) {
                INSTANCE = UpbitRepository()
            }
            return INSTANCE!!
        }
    }
}
