package sample.nackun.com.studyfirst.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import sample.nackun.com.studyfirst.market.Market
import sample.nackun.com.studyfirst.market.Ticker

interface UpbitCommunicable {
    @GET("https://api.upbit.com/v1/market/all")
    fun requestMarket(): Call<ArrayList<Market>>

    @GET("https://api.upbit.com/v1/ticker/")
    fun requestTicker(@Query("markets") markets: String): Call<ArrayList<Ticker>>
}