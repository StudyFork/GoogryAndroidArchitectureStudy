package com.example.architecturestudy.data.source

import com.example.architecturestudy.data.CoinMarketResponse
import com.example.architecturestudy.data.CoinTickerResponse

//CoinsRepository, RemoteDataSource, LocalDataSource 가 implement하는 인터페이스
interface CoinsDataSource {
    interface GetAllMarketCallback {
        fun onAllMarketLoaded(markets: List<CoinMarketResponse>)

        fun onDataNotAvailable()
    }

    interface GetCoinTickerCallback {
        fun onTickerLoaded(tickers: List<CoinTickerResponse>)

        fun onDataNotAvailable()
    }

    fun getAllMarket(callback: GetAllMarketCallback)

    fun getCoinTicker(coin: String, callback: GetCoinTickerCallback)
}