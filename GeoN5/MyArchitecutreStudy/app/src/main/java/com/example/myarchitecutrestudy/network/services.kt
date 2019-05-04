package com.example.myarchitecutrestudy.network

import com.example.myarchitecutrestudy.model.MarketAll
import com.example.myarchitecutrestudy.model.Ticker
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinService{

    //시세 종목 조회 - 마켓 코드 조회
    @GET("market/all")//username diary 조회
    fun getMarket() : Call<List<MarketAll>>

    //시세 Ticker 조회 - 현재가 정보
    @GET("ticker")//id조회
    fun getTicker(@Query("markets")markets:String): Call<List<Ticker>>

}