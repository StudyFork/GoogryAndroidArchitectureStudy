package com.android.studyfork.network.source

import com.android.studyfork.network.model.MarketResponse
import com.android.studyfork.network.model.TickerResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * created by onemask
 */
interface UpbitApi {
    @GET("market/all")
    fun getMarketAll() : Single<List<MarketResponse>>

    @GET("ticker")
    fun getTickers(@Query("markets") markets: String?): Single<List<TickerResponse>>

}