package com.aiden.aiden.architecturepatternstudy.api

import com.aiden.aiden.architecturepatternstudy.api.model.MarketResponse
import com.aiden.aiden.architecturepatternstudy.api.model.TickerResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UpbitApi {

    @GET("v1/market/all/")
    fun getMarketList(): Call<ArrayList<MarketResponse>>

    @GET("v1/ticker/")
    fun getTickerInfo(@Query("markets") markets: String): Call<ArrayList<TickerResponse>>
}