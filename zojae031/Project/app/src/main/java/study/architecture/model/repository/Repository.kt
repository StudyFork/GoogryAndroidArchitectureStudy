package study.architecture.model.repository

import io.reactivex.Flowable
import io.reactivex.Single
import study.architecture.model.vo.Market
import study.architecture.model.vo.Ticker

interface Repository {
    fun getMarketList(): Single<List<Market>>
    fun getTickerList(listName: String): Flowable<MutableList<Ticker>>
}