package com.android.studyfork.network.source

import com.android.studyfork.network.api.UpbitService
import com.android.studyfork.network.model.MarketResponse
import com.android.studyfork.network.model.TickerResponse
import io.reactivex.Single

/**
 * created by onemask
 */
class UpbitDataSourceImpl : UpbitDataSource {
    private val upbitApi = UpbitService.upbitApi

    override fun getMarketAll(): Single<List<MarketResponse>> = upbitApi.getMarketAll()

    override fun getTickers(market: String): Single<List<TickerResponse>> = upbitApi.getTickers(market)

}