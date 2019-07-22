package com.architecture.study.server

import com.architecture.study.model.MarketResponse
import com.architecture.study.model.TickerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class UpbitRequest {
    val URL = "https://api.upbit.com"
    fun getMarketList(listener: UpbitRequestListener<MarketResponse>) {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(ApiService::class.java)

        apiService.getMarketData().enqueue(object : Callback<List<MarketResponse>> {
            override fun onResponse(call: Call<List<MarketResponse>>, response: Response<List<MarketResponse>>) {
                if (response.isSuccessful) {
                    val marketList = response.body()
                    if (marketList != null) {
                        listener.onSucess(marketList)
                    } else {
                        listener.onEmpty("data empty")
                    }
                }
            }

            override fun onFailure(call: Call<List<MarketResponse>>, t: Throwable) {
                t.message?.let {
                    listener.onFailure(it)
                }
            }
        })
    }

    fun getTickerList(marketNames: String, listener: UpbitRequestListener<TickerResponse>) {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(ApiService::class.java)


        val call = apiService.getTickerData(marketNames)
        call.enqueue(object : Callback<List<TickerResponse>> {
            override fun onResponse(call: Call<List<TickerResponse>>, response: Response<List<TickerResponse>>) {
                if (response.isSuccessful) {
                    val tickerList = response.body()
                    if (tickerList != null) {
                        listener.onSucess(tickerList)
                    } else {
                        listener.onEmpty("data empty")
                    }
                }
            }

            override fun onFailure(call: Call<List<TickerResponse>>, t: Throwable) {
                t.message?.let {
                    listener.onFailure(it)
                }
            }
        })
    }

}