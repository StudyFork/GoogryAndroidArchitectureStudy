package com.namjackson.archstudy.network.api

import com.namjackson.archstudy.data.Ticker
import com.namjackson.archstudy.data.UpbitMarket
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UpbitApi {

    @GET(value = "market/all")
    fun getMarketAll(): Call<List<UpbitMarket>>

    @GET(value = "ticker")
    fun getTickers(@Query("markets") markets: String): Call<List<Ticker>>
}