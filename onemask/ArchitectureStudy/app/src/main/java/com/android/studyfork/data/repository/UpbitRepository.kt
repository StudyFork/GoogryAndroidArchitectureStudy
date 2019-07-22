package com.android.studyfork.data.repository

import com.android.studyfork.network.api.UpbitApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * created by onemask
 */
class UpbitRepository(
    private val api: UpbitApi
) : UpbitDatasource {

    override fun getMarketAll() =
        api.getMarketAll()
            .flatMap { markets ->
                val marketName = markets
                    .groupBy {
                        val idx = it.market.indexOf("-")
                        it.market.substring(0, idx)
                    }
                Single.just(marketName)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


    override fun getTikcers(market: String) =
        api.getTikers(market)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

}