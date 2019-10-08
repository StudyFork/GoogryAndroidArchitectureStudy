package com.architecture.study.data.repository

import com.architecture.study.data.source.CoinRemoteDataSource
import com.architecture.study.data.source.CoinRemoteDataSourceListener
import com.architecture.study.network.model.MarketResponse
import com.architecture.study.network.model.TickerResponse

class CoinRepositoryImpl private constructor(
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

    companion object {
        private var instance: CoinRepositoryImpl? = null
        fun getInstance(coinRemoteDataSource: CoinRemoteDataSource): CoinRepositoryImpl =
            instance ?: CoinRepositoryImpl(coinRemoteDataSource).also {
                instance = it
            }

    }
}