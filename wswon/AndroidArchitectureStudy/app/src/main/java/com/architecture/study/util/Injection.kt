package com.architecture.study.util

import com.architecture.study.data.source.CoinRemoteDataSource
import com.architecture.study.data.source.CoinRemoteDataSourceImpl
import com.architecture.study.network.RetrofitInstance

object Injection {
    fun provideCoinRemoteDataSource(): CoinRemoteDataSource =
        CoinRemoteDataSourceImpl.getInstance(RetrofitInstance.getInstance(url))
    private const val url = "https://api.upbit.com"
}