package com.example.architecturestudy.data.source

import com.example.architecturestudy.data.model.Ticker
import com.example.architecturestudy.network.model.MarketResponse

interface CoinDataSource {

    fun getMarketInfo(listener: UpbitListener<MarketResponse>)

    fun getTickerInfo(name: String, listener: UpbitListener<Ticker>)

}