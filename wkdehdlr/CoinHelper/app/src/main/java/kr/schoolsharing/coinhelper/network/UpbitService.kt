package kr.schoolsharing.coinhelper.network

import kr.schoolsharing.coinhelper.model.UpbitMarket
import kr.schoolsharing.coinhelper.model.UpbitTicker
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UpbitService {

    @GET("/v1/market/all")
    fun getUpBitMarket(): Call<List<UpbitMarket>>


    @GET("/v1/ticker")
    fun getUpBitTicker(@Query("markets") market: String): Call<List<UpbitTicker>>
}