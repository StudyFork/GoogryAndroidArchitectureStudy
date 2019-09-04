package com.example.architecturestudy.data.repository

import com.example.architecturestudy.data.model.Ticker
import com.example.architecturestudy.data.source.CoinDataSourceImpl
import com.example.architecturestudy.data.source.UpbitListener
import com.example.architecturestudy.network.model.MarketResponse

class CoinRepositoryImpl: CoinRepository {

    override  fun getMarketInfo(listener: UpbitListener<MarketResponse>) {
        CoinDataSourceImpl.getInstance().getMarketInfo(listener)
    }

    override fun getTickerInfo(name: String, listener: UpbitListener<Ticker>) {
        CoinDataSourceImpl.getInstance()
            .getTickerInfo(name, listener)
    }

    companion object {
        private var instance: CoinRepositoryImpl? = null
        fun getInstance(): CoinRepositoryImpl =
            instance ?: synchronized(this) {
                instance
                    ?: CoinRepositoryImpl().also {
                    instance = it
                }
            }
    }
}