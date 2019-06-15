package sample.nackun.com.studyfirst.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import sample.nackun.com.studyfirst.market.Market
import sample.nackun.com.studyfirst.market.Ticker

interface UpbitApi {
    @GET("market/all")
    fun requestMarket(): Call<ArrayList<Market>>

    @GET("ticker/")
    fun requestTicker(@Query("markets") markets: String): Call<ArrayList<Ticker>>
}