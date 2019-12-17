package com.test.androidarchitecture.data.source

import com.test.androidarchitecture.data.Market
import com.test.androidarchitecture.data.Ticker
import io.reactivex.Single

interface UpbitRepository {

    fun getMarketAll(): Single<List<Market>>

    fun getTicker(marketSearch: String): Single<List<Ticker>>

}