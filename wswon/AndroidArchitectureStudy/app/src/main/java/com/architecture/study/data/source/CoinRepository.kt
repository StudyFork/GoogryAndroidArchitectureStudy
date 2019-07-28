package com.architecture.study.data.source

import com.architecture.study.network.model.MarketResponse
import com.architecture.study.network.model.TickerResponse
import com.architecture.study.network.request.UpbitRequestListener

class CoinRepository : CoinDataSource{

    override fun getMarketList(listener: UpbitRequestListener<MarketResponse>) {
        CoinRemoteDataSource.getMarketList(listener)
    }

    override fun getTickerList(marketNames: String, listener: UpbitRequestListener<TickerResponse>) {
        CoinRemoteDataSource.getTickerList(marketNames, listener)
    }
}