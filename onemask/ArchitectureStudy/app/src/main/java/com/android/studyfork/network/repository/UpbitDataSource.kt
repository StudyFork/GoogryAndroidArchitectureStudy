package com.android.studyfork.network.repository

import com.android.studyfork.network.remote.model.MarketResponse
import com.android.studyfork.network.remote.model.TickerResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface UpbitDataSource {

    @GET("market/all")
    fun getMarketAll() : Single<List<MarketResponse>>

    @GET("ticker")
    fun getTikers(@Query ("markets") markets : String? ): Single<List<TickerResponse>>

}