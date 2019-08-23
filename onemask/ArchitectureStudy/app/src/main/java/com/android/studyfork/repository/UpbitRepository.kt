package com.android.studyfork.repository

import com.android.studyfork.network.model.MarketResponse
import com.android.studyfork.network.model.TickerResponse
import io.reactivex.Single

/**
 * created by onemask
 */
interface UpbitRepository{
    fun getMarketAll() : Single<Map<String, List<MarketResponse>>>
    fun getTickers(market: String): Single<List<TickerResponse>>
}