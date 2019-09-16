package com.jake.archstudy.data.source

import com.jake.archstudy.network.response.MarketResponse
import com.jake.archstudy.network.response.TickerResponse
import com.jake.archstudy.network.service.UpbitService
import retrofit2.Call

interface UpbitDataSource {

    fun getMarketAll(
        success: (List<MarketResponse>) -> Unit,
        failure: (Throwable) -> Unit
    )

    fun getTicker(
        markets: String,
        success: (List<TickerResponse>) -> Unit,
        failure: (Throwable) -> Unit
    )

}