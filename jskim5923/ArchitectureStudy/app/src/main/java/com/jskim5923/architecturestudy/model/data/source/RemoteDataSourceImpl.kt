package com.jskim5923.architecturestudy.model.data.source

import com.jskim5923.architecturestudy.api.CoinApi

class RemoteDataSourceImpl(private val api: CoinApi) : RemoteDataSource {
    override fun getMarketList() = api.getMarketList()

    override fun getTicker(markets: String) = api.getTicker(markets)
}