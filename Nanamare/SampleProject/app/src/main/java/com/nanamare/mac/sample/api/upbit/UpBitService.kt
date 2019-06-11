package com.nanamare.mac.sample.api.upbit

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UpBitService {
    @GET("v1/market/all")
    fun getAllMarketList(): Single<Response<List<MarketModel>>>

    @GET("v1/ticker")
    fun getTickerList(@Query("markets") markets: String) : Single<Response<List<TickerModel>>>

}