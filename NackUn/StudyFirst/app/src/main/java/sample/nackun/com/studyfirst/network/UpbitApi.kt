package sample.nackun.com.studyfirst.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import sample.nackun.com.studyfirst.vo.Market
import sample.nackun.com.studyfirst.vo.Ticker

interface UpbitApi {
    @GET("v1/market/all")
    fun requestMarket(): Call<List<Market>>

    @GET("v1/ticker/")
    fun requestTicker(@Query("markets") markets: String): Call<List<Ticker>>
}