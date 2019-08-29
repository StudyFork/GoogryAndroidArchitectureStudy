package com.android.studyfork.network.source

import com.android.studyfork.network.model.MarketResponse
import com.android.studyfork.network.model.TickerResponse
import io.reactivex.Single

/**
 * created by onemask
 */
interface UpbitDataSource {
    fun getMarketAll(): Single<List<MarketResponse>>
    fun getTickers(market: String): Single<List<TickerResponse>>
}