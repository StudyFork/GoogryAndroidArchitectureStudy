package com.android.studyfork.repository

import com.android.studyfork.repository.model.MarketAllResponse
import com.android.studyfork.repository.model.TickerResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface UpbitApi {

    @GET("market/all")
    fun getMarketAll() : Single<List<MarketAllResponse>>

    @GET("ticker")
    fun getTikers(@Query ("markets") markets : String? ): Single<List<TickerResponse>>

}