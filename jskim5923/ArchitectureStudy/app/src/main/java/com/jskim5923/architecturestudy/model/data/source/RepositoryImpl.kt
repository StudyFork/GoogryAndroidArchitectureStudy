package com.jskim5923.architecturestudy.model.data.source

class RepositoryImpl(private val remoteDataSource: RemoteDataSource) :
    Repository {
    override fun getMarketList() = remoteDataSource.getMarketList()

    override fun getTicker(markets: String) = remoteDataSource.getTicker(markets)
}