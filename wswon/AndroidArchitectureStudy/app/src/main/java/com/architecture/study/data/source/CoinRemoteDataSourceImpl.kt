package com.architecture.study.data.source

import com.architecture.study.network.api.UpbitApi
import com.architecture.study.network.model.MarketResponse
import com.architecture.study.network.model.TickerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoinRemoteDataSourceImpl(private val upbitApi: UpbitApi) :
    CoinRemoteDataSource {

    override fun getMarketList(listener: CoinRemoteDataSourceListener<MarketResponse>) {
        upbitApi.getMarketData().enqueue(object : Callback<List<MarketResponse>> {
            override fun onResponse(
                call: Call<List<MarketResponse>>,
                response: Response<List<MarketResponse>>
            ) {
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

    override fun getTickerList(
        marketNames: String,
        listener: CoinRemoteDataSourceListener<TickerResponse>
    ) {
        upbitApi.getTickerData(marketNames).enqueue(object : Callback<List<TickerResponse>> {
            override fun onResponse(
                call: Call<List<TickerResponse>>,
                response: Response<List<TickerResponse>>
            ) {
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

    companion object {
        private var instance: CoinRemoteDataSourceImpl? = null
        fun getInstance(upbitApi: UpbitApi): CoinRemoteDataSourceImpl =
            instance ?: CoinRemoteDataSourceImpl(upbitApi).also {
                instance = it
            }

    }
}