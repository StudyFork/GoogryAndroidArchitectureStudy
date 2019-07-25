package com.android.studyfork.network.api

import com.android.studyfork.network.remote.model.UpbitMarketResponse
import com.android.studyfork.network.remote.model.UpbitTickerResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface UpbitApi {

    @GET("market/all")
    fun getMarketAll(): Single<List<UpbitMarketResponse>>

    @GET("ticker")
    fun getTickers(@Query("markets") markets: String?): Single<List<UpbitTickerResponse>>

}