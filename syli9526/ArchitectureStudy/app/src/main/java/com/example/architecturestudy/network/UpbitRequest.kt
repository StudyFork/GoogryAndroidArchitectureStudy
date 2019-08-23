package com.example.architecturestudy.network

import com.example.architecturestudy.model.CoinInfo
import com.example.architecturestudy.model.MarketResponse
import com.example.architecturestudy.model.TickerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UpbitRequest {

    private val upbitService = UpbitService.getInstance().retrofit

    fun getMarketInfo(listener: UpbitListener<MarketResponse>) {

        upbitService.getMarketData().enqueue(object : Callback<List<MarketResponse>> {
            override fun onFailure(call: Call<List<MarketResponse>>, t: Throwable) {
                t.message?.let {
                    listener.onFailure(it)
                }
            }

            override fun onResponse(call: Call<List<MarketResponse>>, response: Response<List<MarketResponse>>) {
                response.body()?.let { listener.onResponse(it) } ?: run { listener.onFailure("null") }
            }
        })
    }

    fun getTickerInfo(name: String, listener: UpbitListener<CoinInfo>) {

        upbitService.getTickerData(name).enqueue(object : Callback<List<TickerResponse>> {

            override fun onFailure(call: Call<List<TickerResponse>>, t: Throwable) {
                t.message?.let {
                    listener.onFailure(it)
                }
            }

            override fun onResponse(call: Call<List<TickerResponse>>, response: Response<List<TickerResponse>>) {
                response.body()?.let {

                    val test = it.map {
                        val market = it.market.split("-")
                        val currencyType = market[0]
                        val coinName = market[1]
                        val presentPrice = it.tradePrice
                        val signedChangeRate: Double = it.signedChangeRate
                        val transactionAmount: Double = it.accTradePrice24h

                        CoinInfo(currencyType, coinName, presentPrice, signedChangeRate, transactionAmount)
                    }
                    listener.onResponse(test)

                } ?: run { listener.onFailure("null") }
            }
        })

    }
}