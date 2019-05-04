package com.namjackson.archstudy.data.repository

import com.namjackson.archstudy.data.Ticker
import com.namjackson.archstudy.data.UpbitMarket
import com.namjackson.archstudy.network.api.UpbitApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpbitRepository private constructor(val upbitApi: UpbitApi) {

    companion object {
        private lateinit var instance: UpbitRepository
        fun getInstance(upbit: UpbitApi): UpbitRepository {
            if (!this::instance.isInitialized) {
                instance = UpbitRepository(upbit)
            }
            return instance
        }

    }

    fun getMarketAll(baseCurrency: String, success: (markets: String) -> Unit, fail: (msg: String) -> Unit) {
        upbitApi.getMarketAll().enqueue(object : Callback<List<UpbitMarket>> {
            override fun onResponse(call: Call<List<UpbitMarket>>?, response: Response<List<UpbitMarket>>?) {
                success(response?.body()?.filter {
                    it.market.startsWith(baseCurrency)
                }?.joinToString { it.market  }?:"")
            }

            override fun onFailure(call: Call<List<UpbitMarket>>?, t: Throwable?) {
                fail(t?.message.toString())
            }
        })
    }

    fun getTickers(markets: String, success: (coinList: List<Ticker>) -> Unit, fail: (msg: String) -> Unit) {
        upbitApi.getTickers(markets).enqueue(object : Callback<List<Ticker>> {
            override fun onResponse(call: Call<List<Ticker>>?, response: Response<List<Ticker>>?) {
                success(response?.body().orEmpty().sortedByDescending { it.accTradePrice24h })
            }

            override fun onFailure(call: Call<List<Ticker>>?, t: Throwable?) {
                fail(t?.message.toString())
            }
        })

    }


}