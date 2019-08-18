package com.example.architecturestudy.util

import com.example.architecturestudy.model.MarketResponse
import com.example.architecturestudy.model.TickerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UpbitRequest{

    private val upbitService = UpbitService.getInstance().retrofit

    fun getMarketInfo(listenser: UpbitListener<MarketResponse>) {

        upbitService.getMarketData()?.enqueue(object : Callback<List<MarketResponse>> {
            override fun onFailure(call: Call<List<MarketResponse>>, t: Throwable) {
                t.message?.let {
                    listenser.onFailure(it)
                }
            }

            override fun onResponse(call: Call<List<MarketResponse>>, response: Response<List<MarketResponse>>) {
                response.body()?.let {
                    listenser.onResponse(it)
                }.let { listenser.onFailure("null") }
            }
        })
    }

    fun getTickerInfo(listenser: UpbitListener<TickerResponse>, name: String) {

        upbitService.getTickerData(name).enqueue(object : Callback<List<TickerResponse>> {

            override fun onFailure(call: Call<List<TickerResponse>>, t: Throwable) {
                t.message?.let {
                    listenser.onFailure(it)
                }
            }

            override fun onResponse(call: Call<List<TickerResponse>>, response: Response<List<TickerResponse>>) {
                response.body()?.let {
                    listenser.onResponse(it)
                }.let { listenser.onFailure("null") }
            }
        })

    }
}