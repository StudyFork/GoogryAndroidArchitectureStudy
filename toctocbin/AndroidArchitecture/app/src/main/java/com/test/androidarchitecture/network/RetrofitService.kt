package com.test.androidarchitecture.network

import com.test.androidarchitecture.data.Coin
import com.test.androidarchitecture.data.Market
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("market/all")
    fun loadMarketData(): Single<List<Market>>

    @GET("ticker")
    fun loadCoinData(@Query("markets") markets: String): Single<List<Coin>>

}