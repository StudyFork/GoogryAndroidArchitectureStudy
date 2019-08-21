package com.architecture.study.data.repository

import com.architecture.study.data.source.CoinRemoteDataSource
import com.architecture.study.data.source.CoinRemoteDataSourceImp
import com.architecture.study.data.source.CoinRemoteDataSourceListener
import com.architecture.study.network.RetrofitInstance
import com.architecture.study.network.RetrofitInstanceCallBack
import com.architecture.study.network.api.UpbitApi
import com.architecture.study.network.model.MarketResponse
import com.architecture.study.network.model.TickerResponse

class CoinRepositoryImp : CoinRepository {
    private val upbitUrl = "https://api.upbit.com"

    private var upbitRetrofitInstance: UpbitApi? = null

    private lateinit var coinRemoteDataSource: CoinRemoteDataSource

    companion object {
        private var instance: CoinRepositoryImp? = null
        fun getInstance(): CoinRepositoryImp =
            instance ?: synchronized(this) {
                instance
                    ?: CoinRepositoryImp().also {
                        instance = it
                    }
            }
    }


    // retrofit instance 가져옴
    override fun setRetrofitInstance(callBack: RetrofitInstanceCallBack) {
        RetrofitInstance.getInstance<UpbitApi>(upbitUrl)?.let {
            upbitRetrofitInstance = it
            coinRemoteDataSource = CoinRemoteDataSourceImp.getInstance(it)
        }

        if (upbitRetrofitInstance != null) {
            callBack.onLoaded()
        } else {
            callBack.onNotLoaded()
        }

    }


    // 생성시 Retrofit 객체를 주입해 작동하도록 변경
    override fun getMarketList(listener: CoinRemoteDataSourceListener<MarketResponse>) {
        upbitRetrofitInstance?.let {
            coinRemoteDataSource.getMarketList(listener)
        }
    }

    override fun getTickerList(marketNames: String, listener: CoinRemoteDataSourceListener<TickerResponse>) {
        upbitRetrofitInstance?.let {
            coinRemoteDataSource.getTickerList(marketNames, listener)
        }
    }
}