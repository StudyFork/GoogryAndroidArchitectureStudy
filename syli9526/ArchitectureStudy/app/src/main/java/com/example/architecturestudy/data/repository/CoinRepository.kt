package com.example.architecturestudy.data.repository

import com.example.architecturestudy.data.model.Ticker
import com.example.architecturestudy.data.source.UpbitListener
import com.example.architecturestudy.network.model.MarketResponse

interface CoinRepository {

    fun getMarketInfo(listener: UpbitListener<MarketResponse>)

    fun getTickerInfo(name: String, listener: UpbitListener<Ticker>)

}