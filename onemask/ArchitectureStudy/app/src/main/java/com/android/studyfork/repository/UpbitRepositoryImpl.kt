package com.android.studyfork.repository

import com.android.studyfork.network.source.UpbitDataSourceImpl
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * created by onemask
 */
class UpbitRepositoryImpl : UpbitRepository {
    override fun getMarketAll() =
        UpbitDataSourceImpl.getMarketAll()
            .flatMap { markets ->
                val marketNamesMap = markets
                    .groupBy {
                        val idx = it.market.indexOf("-")
                        it.market.substring(0, idx)
                    }
                Single.just(marketNamesMap)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


    override fun getTickers(market: String) =
        UpbitDataSourceImpl.getTickers(market)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

}