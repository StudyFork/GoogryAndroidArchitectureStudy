package com.architecture.study.data.repository

import com.architecture.study.data.source.CoinRemoteDataSource
import com.architecture.study.data.source.CoinRemoteDataSourceListener
import com.architecture.study.network.RetrofitInstanceCallBack
import com.architecture.study.network.model.MarketResponse
import com.architecture.study.network.model.TickerResponse

class CoinRepositoryImp private constructor(
    private val coinRemoteDataSource: CoinRemoteDataSource?
) : CoinRepository {

    // retrofit instance 가져옴
    override fun setRetrofitInstance(retrofitInstanceCallBack: RetrofitInstanceCallBack) {
        coinRemoteDataSource?.let {
            retrofitInstanceCallBack.onLoaded()
        } ?: retrofitInstanceCallBack.onNotLoaded()
    }

    // 생성시 Retrofit 객체를 주입해 작동하도록 변경
    override fun getMarketList(listener: CoinRemoteDataSourceListener<MarketResponse>) {
        coinRemoteDataSource?.getMarketList(listener)
    }

    override fun getTickerList(
        marketNames: String,
        listener: CoinRemoteDataSourceListener<TickerResponse>
    ) {
        coinRemoteDataSource?.getTickerList(marketNames, listener)
    }

    companion object {
        private var instance: CoinRepositoryImp? = null
        fun getInstance(coinRemoteDataSource: CoinRemoteDataSource?): CoinRepositoryImp =
            instance ?: synchronized(this) {
                instance
                    ?: CoinRepositoryImp(coinRemoteDataSource).also {
                        instance = it
                    }
            }
    }
}