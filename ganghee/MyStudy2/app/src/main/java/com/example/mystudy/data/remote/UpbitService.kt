package com.example.mystudy.data.remote

import com.example.mystudy.data.MarketResponse
import com.example.mystudy.data.TickerResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface UpbitService {

    @GET("market/all")
    fun getMarkets(): Single<List<MarketResponse>>

    @GET("ticker")
    fun getTickers(
        @Query("markets") markets: String
    ): Single<List<TickerResponse>>
}