package com.example.architecturestudy.data.source

import com.example.architecturestudy.data.model.Ticker
import com.example.architecturestudy.network.model.MarketResponse

class CoinRepository: CoinDataSource{


    override  fun getMarketInfo(listener: UpbitListener<MarketResponse>) {
        CoinDataSourceImpl.getInstance().getMarketInfo(listener)
    }

    override fun getTickerInfo(name: String, listener: UpbitListener<Ticker>) {
        CoinDataSourceImpl.getInstance().getTickerInfo(name, listener)
    }

    companion object {
        private var instance: CoinRepository? = null
        fun getInstance(): CoinRepository =
            instance ?: synchronized(this) {
                instance ?: CoinRepository().also {
                    instance = it
                }
            }
    }
}