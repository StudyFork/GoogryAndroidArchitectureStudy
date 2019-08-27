package com.jskim5923.architecturestudy.model.data.source

import com.jskim5923.architecturestudy.model.Market
import com.jskim5923.architecturestudy.model.TickerResponse
import io.reactivex.Single

interface RemoteDataSource {
    fun getMarketList(): Single<List<Market>>

    fun getTicker(markets: String): Single<List<TickerResponse>>
}