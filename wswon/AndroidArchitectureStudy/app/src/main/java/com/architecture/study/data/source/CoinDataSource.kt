package com.architecture.study.data.source

import com.architecture.study.network.model.MarketResponse
import com.architecture.study.network.model.TickerResponse
import com.architecture.study.network.request.UpbitRequestListener

interface CoinDataSource {

    fun getMarketList(listener: UpbitRequestListener<MarketResponse>)

    fun getTickerList(marketNames: String, listener: UpbitRequestListener<TickerResponse>)

}