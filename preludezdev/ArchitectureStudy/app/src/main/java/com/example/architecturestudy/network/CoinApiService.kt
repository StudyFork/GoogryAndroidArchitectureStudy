package com.example.architecturestudy.network

import com.example.architecturestudy.data.CoinMarketResponse
import com.example.architecturestudy.data.CoinTickerResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinApiService{
    @GET("v1/market/all/")
    fun getAllCoinMarket(): Call<List<CoinMarketResponse>>

    @GET("v1/ticker/")
    fun getCurrTicker(@Query("markets") markets: String): Call<List<CoinTickerResponse>>
}