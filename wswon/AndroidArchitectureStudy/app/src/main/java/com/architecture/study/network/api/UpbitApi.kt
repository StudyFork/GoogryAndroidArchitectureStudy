package com.architecture.study.network.api

import com.architecture.study.network.model.MarketResponse
import com.architecture.study.network.model.TickerResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UpbitApi {
    @GET("/v1/market/all")
    fun getMarketData(): Call<List<MarketResponse>>

    @GET("/v1/ticker?")
    fun getTickerData(
        @Query("markets") markets: String
    ): Call<List<TickerResponse>>
}