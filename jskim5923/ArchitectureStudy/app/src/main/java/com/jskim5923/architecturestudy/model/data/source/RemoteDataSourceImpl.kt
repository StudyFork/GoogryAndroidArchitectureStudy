package com.jskim5923.architecturestudy.model.data.source

import com.jskim5923.architecturestudy.api.ApiManager

object RemoteDataSourceImpl : RemoteDataSource {
    private val api = ApiManager.coinApi

    override fun getMarketList() = api.getMarketList()

    override fun getTicker(markets: String) = api.getTicker(markets)
}