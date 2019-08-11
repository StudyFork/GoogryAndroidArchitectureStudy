package study.architecture.model.datasource

import io.reactivex.Single
import study.architecture.model.entity.Market
import study.architecture.model.entity.Ticker

interface UpbitLocalDataSource {
    fun getMarkets(): Single<List<Market>>
    fun getTickers(markets: String): Single<MutableList<Ticker>>
    fun insertMarket(market: Market)
    fun insertTicker(ticker: Ticker)
    fun checkNetwork(): Boolean
}