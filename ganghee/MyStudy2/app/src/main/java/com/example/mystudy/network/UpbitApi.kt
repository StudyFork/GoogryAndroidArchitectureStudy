package com.example.mystudy.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface UpbitApi {

    @GET("market/all")
    fun getMarkets(): Single<List<MarketResponse>>

    @GET("ticker")
    fun getTickers(
        @Query("markets") markets: String
    ): Single<List<TickerResponse>>
}