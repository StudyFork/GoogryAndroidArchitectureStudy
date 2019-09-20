package com.example.mystudy.data

import com.example.mystudy.data.remote.UpbitRemoteDataSourceImpl
import com.example.mystudy.data.remote.UpbitRemoteDataSource
import io.reactivex.Single

/**
 * 가져온 데이터를 가공하는 클래스
 **/

class UpbitRepository (
    private val upbitRemoteDataSource: UpbitRemoteDataSource
){
    fun getMarket(): Single<String> =
        upbitRemoteDataSource.getMarkets()
            .map {
                it.joinToString(",") {
                    it.market
                }
            }

    fun getTicker(marketList: String) =
        UpbitRemoteDataSourceImpl.getTickers(marketList)

    companion object {
        private var INSTANCE: UpbitRepository? = null

        fun getInstance(
            upbitRemoteDataSource: UpbitRemoteDataSource
        ): UpbitRepository = INSTANCE?.let { it } ?: UpbitRepository(upbitRemoteDataSource).apply {
            INSTANCE = this
        }
    }
}