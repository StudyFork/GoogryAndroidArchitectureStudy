package com.test.androidarchitecture.data.source

import javax.inject.Inject


class UpbitRepositoryImpl @Inject constructor(
    private val upbitRemoteDataSourceImpl : UpbitRemoteDataSource
): UpbitRepository {

    override fun getMarketAll() = upbitRemoteDataSourceImpl.getMarketAll()

    override fun getTicker(marketSearch: String) = upbitRemoteDataSourceImpl.getTicker(marketSearch)
}