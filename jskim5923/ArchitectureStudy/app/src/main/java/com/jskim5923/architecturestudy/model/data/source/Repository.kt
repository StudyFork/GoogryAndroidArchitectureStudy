package com.jskim5923.architecturestudy.model.data.source

object Repository : RemoteDataSource {
    override fun getMarketList() = RemoteDataSourceImpl.getMarketList()

    override fun getTicker(markets: String) = RemoteDataSourceImpl.getTicker(markets)
}