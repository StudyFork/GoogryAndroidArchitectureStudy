package com.jskim5923.architecturestudy.api

import com.jskim5923.architecturestudy.model.Market
import com.jskim5923.architecturestudy.model.Ticker
import com.jskim5923.architecturestudy.model.TickerResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinApi {
    @GET("v1/market/all")
    fun getMarketList(): Single<List<Market>>

    @GET("v1/ticker")
    fun getTicker(@Query("markets") markets: String): Single<List<TickerResponse>>
}