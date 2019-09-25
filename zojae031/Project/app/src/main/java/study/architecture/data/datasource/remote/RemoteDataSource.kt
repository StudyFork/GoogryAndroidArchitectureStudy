package study.architecture.data.datasource.remote

import io.reactivex.Single
import study.architecture.data.entity.Market
import study.architecture.data.entity.Ticker

interface RemoteDataSource {
    fun getMarkets(): Single<List<Market>>
    fun getTickers(markets: String): Single<MutableList<Ticker>>
}