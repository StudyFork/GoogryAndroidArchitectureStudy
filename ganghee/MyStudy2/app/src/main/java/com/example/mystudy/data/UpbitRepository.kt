package com.example.mystudy.data

import com.example.mystudy.data.remote.UpbitRemoteDataSource
import com.example.mystudy.data.remote.UpbitService
import io.reactivex.Single

/**
 * 가져온 데이터를 가공하는 클래스
 **/

class UpbitRepository(
    private val upbitService: UpbitService
) {
    fun getMarket(): Single<String> =
        upbitService.getMarkets()
            .map {
                it.joinToString(",") {
                    it.market
                }
            }

    fun getTicker(marketList: String) =
        UpbitRemoteDataSource.getTickers(marketList)

    companion object {
        private var INSTANCE: UpbitRepository? = null

        fun getInstance(
            upbitService: UpbitService
        ): UpbitRepository = INSTANCE?.let { it } ?: UpbitRepository(upbitService).apply {
            INSTANCE = this
        }
    }
}