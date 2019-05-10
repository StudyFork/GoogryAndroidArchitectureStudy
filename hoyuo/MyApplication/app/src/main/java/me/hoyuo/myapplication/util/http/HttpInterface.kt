package me.hoyuo.myapplication.util.http

import io.reactivex.Flowable
import io.reactivex.Single
import me.hoyuo.myapplication.model.upbit.Market
import me.hoyuo.myapplication.model.upbit.Ticker
import retrofit2.http.GET
import retrofit2.http.Query


interface HttpInterface {
    @GET("v1/market/all/")
    fun getMarketList(): Flowable<List<Market>>

    @GET("v1/ticker")
    fun getTickers(
        @Query("markets") list: String
    ): Single<List<Ticker>>
}
