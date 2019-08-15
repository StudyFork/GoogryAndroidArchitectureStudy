package com.example.architecturestudy.util

import com.example.architecturestudy.model.MarketResponse
import com.example.architecturestudy.model.TickerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UpbitRequest {

    val url = "https://api.upbit.com"

    fun getMarketInfo(listenser: UpbitListener<MarketResponse>) {

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        apiService.getMarketData()?.enqueue(object : Callback<List<MarketResponse>> {
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

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        apiService.getTickerData(name).enqueue(object : Callback<List<TickerResponse>> {

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