package com.architecturestudy.data.source

import com.architecturestudy.data.UpbitTicker
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UpbitService {
    @GET("v1/market/all")
    fun getMarkets(): Call<List<UpbitTicker>>

    @GET("v1/ticker")
    fun getTicker(
        @Query("markets")
        list: List<String?>?
    ): Call<List<UpbitTicker>>
}