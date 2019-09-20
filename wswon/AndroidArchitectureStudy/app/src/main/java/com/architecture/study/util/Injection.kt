package com.architecture.study.util

import com.architecture.study.data.source.CoinRemoteDataSource
import com.architecture.study.data.source.CoinRemoteDataSourceImpl
import com.architecture.study.network.RetrofitInstance

object Injection {
    fun provideCoinRemoteDataSource(url: String): CoinRemoteDataSource =
        CoinRemoteDataSourceImpl.getInstance(RetrofitInstance.getInstance(url))
}