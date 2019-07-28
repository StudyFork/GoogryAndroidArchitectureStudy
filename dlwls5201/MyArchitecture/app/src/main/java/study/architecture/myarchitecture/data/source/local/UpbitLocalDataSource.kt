package study.architecture.myarchitecture.data.source.local

import io.reactivex.Single
import study.architecture.myarchitecture.data.model.UpbitMarket
import study.architecture.myarchitecture.data.model.UpbitTicker

interface UpbitLocalDataSource {

    fun getMarkets(): Single<List<UpbitMarket>>

    fun getTickers(key: String): Single<List<UpbitTicker>>

    fun insertMarket(market: UpbitMarket)

    fun insertTicker(ticker: UpbitTicker)

    fun updateMarket(market: UpbitMarket)

    fun updateTicker(ticker: UpbitTicker)

    fun clearTickers(key: String)

    fun clearMarkets()
}