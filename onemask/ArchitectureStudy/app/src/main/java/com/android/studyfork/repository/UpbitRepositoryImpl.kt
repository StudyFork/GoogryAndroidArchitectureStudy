package com.android.studyfork.repository

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * created by onemask
 */
class UpbitRepositoryImpl(
    private val api : UpbitApi
): UpbitRepository {

    override fun getMarketAll() =
        api.getMarketAll()
            .flatMap {  markets->
                val marketNamesKey = markets
                    .groupBy {
                        val idx = it.market.indexOf("-")
                        it.market.substring(0,idx)
                    }
                Single.just(marketNamesKey)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


    override fun getTickers(market: String) =
        api.getTickers(market)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

}