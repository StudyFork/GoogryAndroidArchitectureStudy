package com.example.architecturestudy.data.source

import com.example.architecturestudy.data.model.CoinInfo
import com.example.architecturestudy.network.api.ApiService
import com.example.architecturestudy.network.model.MarketResponse
import com.example.architecturestudy.network.model.TickerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CoinDataSourceImpl : CoinDataSource {


    override fun getMarketInfo(listener: UpbitListener<MarketResponse>) {

        apiService.getMarketData().enqueue(object : Callback<List<MarketResponse>> {
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

    override fun getTickerInfo(name: String, listener: UpbitListener<CoinInfo>) {

        apiService.getTickerData(name).enqueue(object : Callback<List<TickerResponse>> {

            override fun onFailure(call: Call<List<TickerResponse>>, t: Throwable) {
                t.message?.let {
                    listener.onFailure(it)
                }
            }

            override fun onResponse(call: Call<List<TickerResponse>>, response: Response<List<TickerResponse>>) {
                response.body()?.let {

                    val coinInfo = it.map {
                        val market = it.market.split("-")
                        val currencyType = market[0]
                        val coinName = market[1]
                        val presentPrice = it.tradePrice
                        val signedChangeRate: Double = it.signedChangeRate
                        val transactionAmount: Double = it.accTradePrice24h

                        CoinInfo(currencyType, coinName, presentPrice, signedChangeRate, transactionAmount)
                    }
                    listener.onResponse(coinInfo)

                } ?: run { listener.onFailure("null") }
            }
        })

    }

    companion object {
        private const val URL = "https://api.upbit.com"
        private val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        private val apiService = retrofit.create(ApiService::class.java)

        private var instance: CoinDataSourceImpl? = null
        fun getInstance(): CoinDataSourceImpl =
            instance ?: synchronized(this) {
                instance ?: CoinDataSourceImpl().also {
                    instance = it
                }
            }
    }
}