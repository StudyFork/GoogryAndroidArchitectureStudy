package kr.schoolsharing.coinhelper.data

import kr.schoolsharing.coinhelper.data.local.UpbitLocalDataSource
import kr.schoolsharing.coinhelper.data.remote.UpbitRemoteDataSource

class Repository : UpbitDataSource {

    private val upbitRemoteDataSource: UpbitRemoteDataSource = UpbitRemoteDataSource
    private val upbitLocalDataSource: UpbitLocalDataSource = UpbitLocalDataSource


    fun getTickerFromLocal(onSuccess: () -> Unit, onError: (Throwable) -> Unit) {
        try {
            onSuccess()
        } catch (e: Throwable) {
            onError(e)
        }
    }

    fun getTickerFromApi(onSuccess: () -> Unit, onError: (Throwable) -> Unit) {
        try {
            onSuccess()
        } catch (e: Throwable) {
            onError(e)
        }
    }


    override fun getMarket(callback: UpbitDataSource.GetMarketCallback) {
        getMarketFromRemoteDataSource(callback)
    }

    override fun getTicker(markets: String, callback: UpbitDataSource.GetTickerCallback) {
        getTickerFromRemoteDataSource(markets, callback)
    }

    private fun getMarketFromRemoteDataSource(callback: UpbitDataSource.GetMarketCallback) {
        upbitRemoteDataSource.getMarket(object : UpbitDataSource.GetMarketCallback {
            override fun onMarketLoaded(markets: List<UpbitMarket>) {
                callback.onMarketLoaded(markets)
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }
        })
    }

    private fun getTickerFromRemoteDataSource(markets: String, callback: UpbitDataSource.GetTickerCallback) {
        upbitRemoteDataSource.getTicker(markets, object : UpbitDataSource.GetTickerCallback {
            override fun onTickerLoaded(tickers: List<UpbitTicker>) {
                callback.onTickerLoaded(tickers)
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }
        })

    }
}