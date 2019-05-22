package com.namjackson.archstudy.data.source.remote.upbit

import com.namjackson.archstudy.data.source.remote.upbit.response.UpbitMarket
import com.namjackson.archstudy.data.source.remote.upbit.response.UpbitTickerResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UpbitApi {

    @GET(value = "market/all")
    fun getMarketAll(): Call<List<UpbitMarket>>

    @GET(value = "ticker")
    fun getTickers(@Query("markets") markets: String): Call<List<UpbitTickerResponse>>
}