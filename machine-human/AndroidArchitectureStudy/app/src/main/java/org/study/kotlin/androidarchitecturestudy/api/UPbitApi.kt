package org.study.kotlin.androidarchitecturestudy.api

import io.reactivex.Single
import org.study.kotlin.androidarchitecturestudy.api.model.MarketModel
import org.study.kotlin.androidarchitecturestudy.api.model.TickerModel
import retrofit2.http.GET
import retrofit2.http.Query

interface UpbitApi {
    @GET("v1/market/all")
    fun getMarket(): Single<List<MarketModel>>

    @GET("v1/ticker")
    fun getTicker(@Query("markets") markets: String): Single<List<TickerModel>>
}