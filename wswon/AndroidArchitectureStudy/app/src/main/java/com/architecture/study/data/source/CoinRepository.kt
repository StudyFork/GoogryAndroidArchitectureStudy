package com.architecture.study.data.source

import com.architecture.study.network.model.MarketResponse
import com.architecture.study.network.model.TickerResponse

class CoinRepository : CoinDataSource {
    companion object {
        private var instance: CoinRepository? = null
        fun getInstance(): CoinRepository =
            instance ?: synchronized(this) {
                instance ?: CoinRepository().also {
                    instance = it
                }
            }
    }

    override fun getMarketList(listener: CoinRemoteDataSourceListener<MarketResponse>) {
        CoinRemoteDataSource.getInstance().getMarketList(listener)
    }

    override fun getTickerList(marketNames: String, listener: CoinRemoteDataSourceListener<TickerResponse>) {
        CoinRemoteDataSource.getInstance().getTickerList(marketNames, listener)
    }
}