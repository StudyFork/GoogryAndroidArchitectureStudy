package com.architecture.study.data.repository

import com.architecture.study.data.source.CoinRemoteDataSourceImp
import com.architecture.study.data.source.CoinRemoteDataSourceListener
import com.architecture.study.network.RetrofitInstance
import com.architecture.study.network.RetrofitInstanceCallBack
import com.architecture.study.network.api.UpbitApi
import com.architecture.study.network.model.MarketResponse
import com.architecture.study.network.model.TickerResponse

class CoinRepositoryImp : CoinRepository {
    private val upbitUrl = "https://api.upbit.com"

    private lateinit var upbitRetrofitInstance: UpbitApi

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
        upbitRetrofitInstance = RetrofitInstance.getInstance<UpbitApi>(upbitUrl)

        if (upbitRetrofitInstance != null) {
            callBack.onLoadded()
        } else {
            callBack.onNotLoadded()
        }

    }


    // 생성시 Retrofit 객체를 주입해 작동하도록 변경
    override fun getMarketList(listener: CoinRemoteDataSourceListener<MarketResponse>) {
        CoinRemoteDataSourceImp.getInstance(upbitRetrofitInstance)
            .getMarketList(listener)
    }

    override fun getTickerList(marketNames: String, listener: CoinRemoteDataSourceListener<TickerResponse>) {
        CoinRemoteDataSourceImp.getInstance(upbitRetrofitInstance)
            .getTickerList(marketNames, listener)
    }
}