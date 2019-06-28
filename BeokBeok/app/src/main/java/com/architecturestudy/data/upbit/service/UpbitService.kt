package com.architecturestudy.data.upbit.service

import com.architecturestudy.data.upbit.UpbitTicker
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UpbitService {
    @GET("v1/market/all")
    fun getMarkets(): Single<Response<List<UpbitTicker>>>

    @GET("v1/ticker")
    fun getTicker(
        @Query("markets")
        list: List<String?>?
    ): Single<Response<List<UpbitTicker>>>
}