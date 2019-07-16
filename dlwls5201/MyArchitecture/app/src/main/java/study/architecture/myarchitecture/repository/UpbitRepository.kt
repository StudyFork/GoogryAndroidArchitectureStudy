package study.architecture.myarchitecture.repository

import io.reactivex.Single
import study.architecture.myarchitecture.network.model.UpbitMarket
import study.architecture.myarchitecture.network.model.UpbitTicker

interface UpbitRepository {

    fun getGroupedMarkets(): Single<Map<String, List<UpbitMarket>>>

    fun getTickers(markets: String): Single<List<UpbitTicker>>
}