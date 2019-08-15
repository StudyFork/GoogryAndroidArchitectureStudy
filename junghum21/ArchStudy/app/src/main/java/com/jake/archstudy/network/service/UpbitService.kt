package com.jake.archstudy.network.service

import com.jake.archstudy.network.response.MarketResponse
import retrofit2.Call
import retrofit2.http.GET

interface UpbitService {

    @GET("/v1/market/all")
    fun getMarketAll(): Call<List<MarketResponse>>

}