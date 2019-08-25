package com.jake.archstudy.data.source

import com.jake.archstudy.network.response.MarketResponse
import com.jake.archstudy.network.response.TickerResponse

class UpbitRepository(private val upbitRemoteDataSource: UpbitRemoteDataSource) : UpbitDataSource {

    override fun getMarketAll(
        success: (List<MarketResponse>) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        upbitRemoteDataSource.getMarketAll(success, failure)
    }

    override fun getTicker(
        markets: String,
        success: (List<TickerResponse>) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        upbitRemoteDataSource.getTicker(markets, success, failure)
    }

}