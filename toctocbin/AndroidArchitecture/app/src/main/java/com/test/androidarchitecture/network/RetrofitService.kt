package com.test.androidarchitecture.network

import com.test.androidarchitecture.data.Ticker
import com.test.androidarchitecture.data.Market
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("v1/market/all")
    fun loadMarketData(): Single<List<Market>>

    @GET("v1/ticker")
    fun loadTickerData(@Query("markets")
                       markets: String
    ): Single<List<Ticker>>

}