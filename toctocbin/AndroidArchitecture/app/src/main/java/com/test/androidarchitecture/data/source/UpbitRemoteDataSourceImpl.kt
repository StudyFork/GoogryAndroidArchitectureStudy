package com.test.androidarchitecture.data.source

import com.test.androidarchitecture.data.Market
import com.test.androidarchitecture.data.Ticker
import com.test.androidarchitecture.network.RetrofitService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UpbitRemoteDataSourceImpl @Inject constructor(
    private val retrofitService: RetrofitService
): UpbitRemoteDataSource {

    override fun getMarketAll(): Single<List<Market>> {
        return retrofitService.loadMarketData()
            .subscribeOn(Schedulers.io())
    }

    override fun getTicker(marketSearch: String): Single<List<Ticker>> {
        return retrofitService.loadTickerData(marketSearch)
            .subscribeOn(Schedulers.io())
    }
}