package com.architecturestudy.data.source

import com.architecturestudy.data.UpBitTicker
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UpBitDataSource {
    interface GetTickerCallback {
        fun onThickerLoaded(marketPrice: List<UpBitTicker>)

        fun onDataNotAvailable(err: String?)
    }

    interface Service {
        @GET("market/all")
        fun getMarkets(): Call<List<UpBitTicker>>

        @GET("ticker")
        fun getTicker(@Query("markets") list: List<String?>?): Call<List<UpBitTicker>>
    }

    fun getMarketPrice(prefix: String, callback: GetTickerCallback)
}