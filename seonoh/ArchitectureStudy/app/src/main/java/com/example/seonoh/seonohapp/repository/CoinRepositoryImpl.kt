package com.example.seonoh.seonohapp.repository

import com.example.seonoh.seonohapp.datasource.CoinDataSourceImpl

class CoinRepositoryImpl : CoinRepository {

    private val coinDataSource = CoinDataSourceImpl()

    override fun sendMarket() = coinDataSource.getMarket()

    override fun sendCurrentPriceInfo(
        markets: String?
    ) = coinDataSource.getCurrentPriceInfo(markets!!)
}