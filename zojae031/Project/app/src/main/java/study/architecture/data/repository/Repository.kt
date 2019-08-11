package study.architecture.data.repository

import io.reactivex.Single
import study.architecture.data.entity.Market
import study.architecture.data.entity.Ticker

interface Repository {
    fun getMarkets(): Single<List<Market>>
    fun getTickers(markets: String): Single<MutableList<Ticker>>
}