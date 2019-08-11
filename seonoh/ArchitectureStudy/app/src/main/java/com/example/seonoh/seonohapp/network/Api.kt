package com.example.seonoh.seonohapp.network

import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel
import com.example.seonoh.seonohapp.model.Market
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("/v1/ticker")
    fun getCurrentPriceInfo(
        @Query("markets") markets: String): Single<ArrayList<CurrentPriceInfoModel>>

    @GET("/v1/market/all")
    fun getMarketAll(): Single<ArrayList<Market>>
}