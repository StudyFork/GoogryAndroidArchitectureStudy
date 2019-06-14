package com.aiden.aiden.architecturepatternstudy.api

import com.aiden.aiden.architecturepatternstudy.api.model.MarketModel
import com.aiden.aiden.architecturepatternstudy.api.model.TickerModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UpbitApi {
    @GET("v1/market/all/")
    fun getMarketList(): Call<ArrayList<MarketModel>>

    @GET("v1/ticker/")
    fun getTickerInfo(@Query("markets") markets: String): Call<ArrayList<TickerModel>>
}