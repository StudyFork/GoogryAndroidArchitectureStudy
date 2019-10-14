package com.architecture.study.data.repository

import com.architecture.study.data.source.remote.CoinRemoteDataSourceListener
import com.architecture.study.network.model.MarketResponse
import com.architecture.study.network.model.TickerResponse

interface CoinRepository {
    fun getMarketList(listener: CoinRemoteDataSourceListener<MarketResponse>)

    fun getTickerList(marketNames: String, listener: CoinRemoteDataSourceListener<TickerResponse>)
}