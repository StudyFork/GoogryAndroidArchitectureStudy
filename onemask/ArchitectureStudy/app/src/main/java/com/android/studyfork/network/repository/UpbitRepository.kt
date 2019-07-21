package com.android.studyfork.network.repository

import com.android.studyfork.network.remote.model.MarketResponse
import com.android.studyfork.network.remote.model.TickerResponse
import io.reactivex.Single

/**
 * created by onemask
 */
interface UpbitRepository{
    fun getMarketAll() : Single<Map<String,List<MarketResponse>>>
    fun getTikcers(margket : String) : Single<List<TickerResponse>>
}