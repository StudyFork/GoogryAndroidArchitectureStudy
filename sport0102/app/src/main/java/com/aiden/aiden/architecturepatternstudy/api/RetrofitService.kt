package com.aiden.aiden.architecturepatternstudy.api

import com.aiden.aiden.architecturepatternstudy.api.model.TickerModel
import com.aiden.aiden.architecturepatternstudy.api.model.MarketModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("v1/market/all/")
    fun getMarketList(): Call<ArrayList<MarketModel>>

    @GET("v1/ticker/")
    fun getTickerInfo(@Query("markets") markets: String): Call<ArrayList<TickerModel>>

}