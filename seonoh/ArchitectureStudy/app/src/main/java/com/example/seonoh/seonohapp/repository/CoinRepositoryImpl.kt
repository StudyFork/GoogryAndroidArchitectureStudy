package com.example.seonoh.seonohapp.repository

import com.example.seonoh.seonohapp.datasource.CoinDataSourceImpl
import com.example.seonoh.seonohapp.network.Api
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(coinApi: Api) : CoinRepository {

    private val coinDataSource = CoinDataSourceImpl(coinApi)

    override fun sendMarket() = coinDataSource.getMarket()

    override fun sendCurrentPriceInfo(
        markets: String
    ) = coinDataSource.getCurrentPriceInfo(markets)
}