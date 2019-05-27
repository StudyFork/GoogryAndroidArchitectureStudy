package com.namjackson.archstudy.data.source

import com.namjackson.archstudy.data.model.Ticker
import com.namjackson.archstudy.data.source.local.TickerLocalDataSource
import com.namjackson.archstudy.data.source.remote.TickerRemoteDataSource

class TickerRepository private constructor(
    val tickerLocalDataSource: TickerLocalDataSource,
    val tickerRemoteDataSource: TickerRemoteDataSource
) {

    fun getMarketAll(
        baseCurrency: String,
        success: (markets: String) -> Unit,
        fail: (msg: String) -> Unit
    ) {
        tickerRemoteDataSource.getMarketAll(baseCurrency, success, fail)
    }

    fun getTickers(
        markets: String,
        success: (coinList: List<Ticker>) -> Unit,
        fail: (msg: String) -> Unit
    ) {
        tickerRemoteDataSource.getTickers(markets, success, fail)
    }

    companion object {
        private lateinit var instance: TickerRepository
        fun getInstance(
            tickerLocalDataSource: TickerLocalDataSource,
            tickerRemoteDataSource: TickerRemoteDataSource
        ): TickerRepository {
            if (!this::instance.isInitialized) {
                instance = TickerRepository(tickerLocalDataSource, tickerRemoteDataSource)
            }
            return instance
        }
    }


}