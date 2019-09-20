package com.example.architecturestudy.data.repository

import com.example.architecturestudy.data.model.Ticker
import com.example.architecturestudy.data.source.CoinDataSourceImpl
import com.example.architecturestudy.data.source.UpbitListener
import com.example.architecturestudy.network.model.MarketResponse

class CoinRepositoryImpl(private val coinDataSourceImpl: CoinDataSourceImpl) : CoinRepository {


    override fun getMarketInfo(listener: UpbitListener<MarketResponse>) {
        coinDataSourceImpl.getMarketInfo(listener)
    }

    override fun getTickerInfo(name: String, listener: UpbitListener<Ticker>) {
        coinDataSourceImpl.getTickerInfo(name, listener)
    }

}