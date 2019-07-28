package com.architecture.study.data.repository

import com.architecture.study.data.source.CoinRemoteDataSourceImp
import com.architecture.study.data.source.CoinRemoteDataSourceListener
import com.architecture.study.network.RetrofitInstance
import com.architecture.study.network.api.UpbitApi
import com.architecture.study.network.model.MarketResponse
import com.architecture.study.network.model.TickerResponse

class CoinRepositoryImp : CoinRepository {

    private val upbitUrl = "https://api.upbit.com"

    companion object {
        private var instance: CoinRepositoryImp? = null
        fun getInstance(): CoinRepositoryImp =
            instance ?: synchronized(this) {
                instance
                    ?: CoinRepositoryImp().also {
                        instance = it
                    }
            }
    }

    override fun getMarketList(listener: CoinRemoteDataSourceListener<MarketResponse>) {
        CoinRemoteDataSourceImp.getInstance(RetrofitInstance.getInstance<UpbitApi>(upbitUrl))
            .getMarketList(listener)
    }

    override fun getTickerList(marketNames: String, listener: CoinRemoteDataSourceListener<TickerResponse>) {
        CoinRemoteDataSourceImp.getInstance(RetrofitInstance.getInstance<UpbitApi>(upbitUrl))
            .getTickerList(marketNames, listener)
    }
}