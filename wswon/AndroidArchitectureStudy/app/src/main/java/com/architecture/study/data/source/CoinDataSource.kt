package com.architecture.study.data.source

import com.architecture.study.network.model.MarketResponse
import com.architecture.study.network.model.TickerResponse

interface CoinDataSource {

    fun getMarketList(listener: CoinRemoteDataSourceListener<MarketResponse>)

    fun getTickerList(marketNames: String, listener: CoinRemoteDataSourceListener<TickerResponse>)

}