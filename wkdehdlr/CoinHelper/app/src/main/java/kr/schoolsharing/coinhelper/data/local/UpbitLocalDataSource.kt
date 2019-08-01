package kr.schoolsharing.coinhelper.data.local

import kr.schoolsharing.coinhelper.data.UpbitDataSource

object UpbitLocalDataSource : UpbitDataSource {

    override fun getMarket(callback: UpbitDataSource.GetMarketCallback) {
    }

    override fun getTicker(markets: String, callback: UpbitDataSource.GetTickerCallback) {
    }
}