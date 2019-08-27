package com.test.androidarchitecture.data.source

import com.test.androidarchitecture.data.Market
import com.test.androidarchitecture.data.Ticker
import com.test.androidarchitecture.network.RetrofitClient
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class UpbitRemoteDataSourceImpl private constructor() : UpbitRemoteDataSource {

    private val retrofitService = RetrofitClient.getInstance().retrofitService

    override fun getMarketAll(): Single<List<Market>> {
        return retrofitService.loadMarketData()
            .subscribeOn(Schedulers.io())
    }

    override fun getTicker(marketSearch: String): Single<List<Ticker>> {
        return retrofitService.loadTickerData(marketSearch)
            .subscribeOn(Schedulers.io())
    }

    companion object {

        @Volatile
        private var instance: UpbitRemoteDataSourceImpl? = null

        @JvmStatic
        fun getInstance(): UpbitRemoteDataSourceImpl =
            instance ?: synchronized(this) {
                instance ?: UpbitRemoteDataSourceImpl().also {
                    instance = it
                }
            }
    }

}