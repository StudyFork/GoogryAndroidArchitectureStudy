package com.architecturestudy.data.source

import com.architecturestudy.data.UpbitTicker
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UpbitDataSource {
    interface GetTickerCallback {
        fun onTickerLoaded(marketPrice: List<UpbitTicker>)

        fun onDataNotAvailable(err: String?)
    }

    interface Service {
        @GET("market/all")
        fun getMarkets(): Call<List<UpbitTicker>>

        @GET("ticker")
        fun getTicker(@Query("markets") list: List<String?>?): Call<List<UpbitTicker>>
    }

    fun getMarketPrice(prefix: String, callback: GetTickerCallback)
}