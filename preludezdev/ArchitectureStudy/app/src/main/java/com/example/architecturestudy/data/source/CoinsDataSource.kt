package com.example.architecturestudy.data.source

import com.example.architecturestudy.data.CoinMarketResponse
import com.example.architecturestudy.data.CoinTickerResponse

//CoinsRepository, RemoteDataSource, LocalDataSource 가 implement하는 인터페이스
interface CoinsDataSource {
    interface getAllMarketCallback {
        fun onAllMarketLoaded(list: List<CoinMarketResponse>)

        fun onDataNotAvailable()
    }

    interface getCoinTickerCallback {
        fun onTickerLoaded(list: List<CoinTickerResponse>)

        fun onDataNotAvailable()
    }


    fun getAllMarket(callback: getAllMarketCallback)

    fun getCoinTicker(callback: getCoinTickerCallback)
}