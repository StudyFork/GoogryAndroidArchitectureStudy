package kr.schoolsharing.coinhelper.data

import kr.schoolsharing.coinhelper.model.UpbitMarket
import kr.schoolsharing.coinhelper.model.UpbitTicker

interface UpbitDataSource {

    interface GetMarketCallback {

        fun onMarketLoaded(markets: List<UpbitMarket>)
        fun onDataNotAvailable(t: Throwable)

    }

    interface GetTickerCallback {

        fun onTickerLoaded(tickers: List<UpbitTicker>)
        fun onDataNotAvailable(t: Throwable)

    }

    fun getMarket(callback: GetMarketCallback)
    fun getTicker(markets: String, callback: GetTickerCallback)

}