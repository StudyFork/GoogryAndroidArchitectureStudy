package com.namjackson.archstudy.data.source.remote

import com.namjackson.archstudy.data.model.Ticker
import com.namjackson.archstudy.data.source.remote.upbit.UpbitApi
import com.namjackson.archstudy.data.source.remote.upbit.response.UpbitMarket
import com.namjackson.archstudy.data.source.remote.upbit.response.UpbitTickerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TickerRemoteDataSourceImpl(val upbitApi: UpbitApi) : TickerRemoteDataSource {

    override fun getMarketAll(baseCurrency: String, success: (markets: String) -> Unit, fail: (msg: String) -> Unit) {
        upbitApi.getMarketAll().enqueue(object : Callback<List<UpbitMarket>> {
            override fun onResponse(call: Call<List<UpbitMarket>>?, response: Response<List<UpbitMarket>>?) {
                success(response?.body()?.filter {
                    it.market.startsWith(baseCurrency)
                }?.joinToString { it.market } ?: "")
            }

            override fun onFailure(call: Call<List<UpbitMarket>>?, t: Throwable?) {
                fail(t?.message.toString())
            }
        })
    }

    override fun getTickers(markets: String, success: (tickers: List<Ticker>) -> Unit, fail: (msg: String) -> Unit) {
        upbitApi.getTickers(markets).enqueue(object : Callback<List<UpbitTickerResponse>> {
            override fun onResponse(
                call: Call<List<UpbitTickerResponse>>?,
                response: Response<List<UpbitTickerResponse>>?
            ) {
                success(response?.body().orEmpty().sortedByDescending { it.accTradePrice24h }.map { Ticker.from(it) })
            }

            override fun onFailure(call: Call<List<UpbitTickerResponse>>?, t: Throwable?) {
                fail(t?.message.toString())
            }
        })
    }

    companion object {
        private lateinit var instance: TickerRemoteDataSource
        fun getInstance(upbit: UpbitApi): TickerRemoteDataSource {
            if (!this::instance.isInitialized) {
                instance = TickerRemoteDataSourceImpl(upbit)
            }
            return instance
        }
    }
}