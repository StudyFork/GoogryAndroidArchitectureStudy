package com.test.androidarchitecture.data.source


object UpbitRepository : UpbitRemoteDataSource {

    private val upbitRemoteDataSourceImpl = UpbitRemoteDataSourceImpl

    override fun getMarketAll() = upbitRemoteDataSourceImpl.getMarketAll()

    override fun getTicker(marketSearch: String) = upbitRemoteDataSourceImpl.getTicker(marketSearch)

}