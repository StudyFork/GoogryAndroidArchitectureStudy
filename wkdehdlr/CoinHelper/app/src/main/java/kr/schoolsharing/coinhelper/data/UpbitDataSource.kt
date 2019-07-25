package kr.schoolsharing.coinhelper.data

interface UpbitDataSource {

    interface GetMarketCallback {

        fun onMarketLoaded(markets: List<UpbitMarket>)
        fun onDataNotAvailable()

    }

    interface GetTickerCallback {

        fun onTickerLoaded(tickers: List<UpbitTicker>)
        fun onDataNotAvailable()

    }

    fun getMarket(callback: GetMarketCallback)
    fun getTicker(markets: String, callback: GetTickerCallback)

}