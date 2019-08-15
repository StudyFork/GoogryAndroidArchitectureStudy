package com.jake.archstudy.network.service

import com.jake.archstudy.network.response.MarketResponse
import com.jake.archstudy.network.response.TickerResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UpbitService {

    @GET("/v1/market/all")
    fun getMarketAll(): Call<List<MarketResponse>>

    @GET("/v1/ticker")
    fun getTicker(@Query("markets") markets: String): Call<List<TickerResponse>>

}