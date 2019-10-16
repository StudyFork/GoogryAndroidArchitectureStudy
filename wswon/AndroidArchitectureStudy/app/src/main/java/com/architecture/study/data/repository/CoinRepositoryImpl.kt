package com.architecture.study.data.repository

import com.architecture.study.data.source.remote.CoinRemoteDataSource
import com.architecture.study.data.source.remote.CoinRemoteDataSourceListener
import com.architecture.study.network.model.MarketResponse
import com.architecture.study.network.model.TickerResponse

class CoinRepositoryImpl (
    private val coinRemoteDataSource: CoinRemoteDataSource
) : CoinRepository {

    override fun getMarketList(listener: CoinRemoteDataSourceListener<MarketResponse>) {
        coinRemoteDataSource.getMarketList(listener)
    }

    override fun getTickerList(
        marketNames: String,
        listener: CoinRemoteDataSourceListener<TickerResponse>
    ) {
        coinRemoteDataSource.getTickerList(marketNames, listener)
    }
}