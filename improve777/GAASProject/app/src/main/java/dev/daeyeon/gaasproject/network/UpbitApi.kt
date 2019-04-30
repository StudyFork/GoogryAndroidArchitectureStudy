package dev.daesin.gaasdy.network

import dev.daesin.gaasdy.data.MarketResponse
import dev.daesin.gaasdy.data.TickerResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UpbitApi {

    @GET("market/all")
    fun getMarketCode(): Call<List<MarketResponse>>

    @GET("ticker")
    fun getTicker(@Query("markets") markets: String): Call<List<TickerResponse>>
}