package com.architecture.study.data.source

import com.architecture.study.network.RetrofitInstance
import com.architecture.study.network.api.UpbitApi
import com.architecture.study.network.model.MarketResponse
import com.architecture.study.network.model.TickerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoinRemoteDataSourceImp : CoinRemoteDataSource { //리모트 만들때 파라미터로 API넣게

    companion object {
        private const val URL = "https://api.upbit.com"

        private var instance: CoinRemoteDataSourceImp? = null
        fun getInstance(): CoinRemoteDataSourceImp =
            instance ?: synchronized(this) {
                instance ?: CoinRemoteDataSourceImp().also {
                    instance = it
                }
            }
    }


    override fun getMarketList(listener: CoinRemoteDataSourceListener<MarketResponse>) {
        RetrofitInstance.getInstance<UpbitApi>(URL).getMarketData().enqueue(object : Callback<List<MarketResponse>> {
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
        RetrofitInstance.getInstance<UpbitApi>(URL).getTickerData(marketNames).enqueue(object : Callback<List<TickerResponse>> {
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