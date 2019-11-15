package com.test.androidarchitecture.data.source

import javax.inject.Inject


class UpbitRepository @Inject constructor(
    private val upbitRemoteDataSourceImpl : UpbitRemoteDataSourceImpl
): UpbitRemoteDataSource {

    override fun getMarketAll() = upbitRemoteDataSourceImpl.getMarketAll()

    override fun getTicker(marketSearch: String) = upbitRemoteDataSourceImpl.getTicker(marketSearch)
}