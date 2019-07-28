package com.architecture.study.data.source

import com.architecture.study.network.api.UpbitApi
import com.architecture.study.network.model.MarketResponse
import com.architecture.study.network.model.TickerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CoinRemoteDataSource : CoinDataSource {

    companion object {
        private const val URL = "https://api.upbit.com"
        private val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        private val apiService = retrofit.create(UpbitApi::class.java)

        private var instance: CoinRemoteDataSource? = null
        fun getInstance(): CoinRemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: CoinRemoteDataSource().also {
                    instance = it
                }
            }
    }


    override fun getMarketList(listener: CoinRemoteDataSourceListener<MarketResponse>) {

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

    override fun getTickerList(marketNames: String, listener: CoinRemoteDataSourceListener<TickerResponse>) {

        apiService.getTickerData(marketNames).enqueue(object : Callback<List<TickerResponse>> {
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