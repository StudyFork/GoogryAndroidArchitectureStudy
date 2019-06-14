package com.studyfirstproject.network

import com.studyfirstproject.model.MarketModel
import com.studyfirstproject.model.TickerModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinApi {
    @GET("/v1/market/all")
    fun getAllMarket(): Call<List<MarketModel>>

    @GET("/v1/ticker")
    fun getTickers(@Query("markets") markets: String): Call<List<TickerModel>>
}