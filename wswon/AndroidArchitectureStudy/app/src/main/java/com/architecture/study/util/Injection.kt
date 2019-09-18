package com.architecture.study.util

import com.architecture.study.data.source.CoinRemoteDataSource
import com.architecture.study.data.source.CoinRemoteDataSourceImp
import com.architecture.study.network.RetrofitInstance
import com.architecture.study.network.api.UpbitApi

object Injection {
    fun provideCoinRemoteDataSource(): CoinRemoteDataSource? {
        val upbitUrl = "https://api.upbit.com"
        var coinRemoteDataSource: CoinRemoteDataSource? = null

        RetrofitInstance.getInstance<UpbitApi>(upbitUrl)?.let {
            coinRemoteDataSource = CoinRemoteDataSourceImp.getInstance(it)
        }
        return coinRemoteDataSource
    }
}