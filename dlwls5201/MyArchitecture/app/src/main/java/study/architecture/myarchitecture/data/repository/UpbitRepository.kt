package study.architecture.myarchitecture.data.repository

import io.reactivex.Single
import study.architecture.myarchitecture.data.model.UpbitMarket
import study.architecture.myarchitecture.data.model.UpbitTicker

interface UpbitRepository {

    fun getGroupedMarkets(): Single<Map<String, List<UpbitMarket>>>

    fun getTickers(markets: String): Single<List<UpbitTicker>>
}