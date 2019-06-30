package sample.nackun.com.studyfirst.data

import sample.nackun.com.studyfirst.vo.Ticker

interface DataSource {
    interface RequestTickersCallback {
        fun onTickersLoaded(tickers: List<Ticker>)

        fun onError(t: Throwable)
    }

    fun requestMarkets(marketLike: String, callback: RequestTickersCallback)
}