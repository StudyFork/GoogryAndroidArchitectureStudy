package com.example.architecturestudy.network

import com.example.architecturestudy.data.CoinMarketResponse
import retrofit2.Call
import retrofit2.http.GET

interface CoinApiService{
    @GET("v1/market/all/")
    fun getAllCoinMarket(): Call<List<CoinMarketResponse>>
}