package dev.daeyeon.gaasproject.network

import dev.daeyeon.gaasproject.data.response.MarketResponse
import dev.daeyeon.gaasproject.data.response.TickerResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UpbitApi {

    @GET("market/all")
    fun getMarketCode(): Call<List<MarketResponse>>

    @GET("ticker")
    fun getTicker(@Query("markets") markets: String): Call<List<TickerResponse>>
}