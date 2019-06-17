package com.architecturestudy.data.source

import com.architecturestudy.data.UpBitTicker
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UpBitRetrofitDataSource : UpBitDataSource {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.upbit.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(
            OkHttpClient.Builder().addInterceptor(
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            ).build()
        )
        .build()
        .create(UpBitDataSource.Service::class.java)

    override fun getMarketPrice(prefix: String, callback: UpBitDataSource.GetTickerCallback) {
        retrofit.getMarkets().enqueue(object : Callback<List<UpBitTicker>> {
            override fun onResponse(call: Call<List<UpBitTicker>>, response: Response<List<UpBitTicker>>) {
                val markets = response.body() ?: return
                val tickers = markets
                    .asSequence()
                    .filter { hasPrefix(prefix) }
                    .filter { it.market!!.startsWith(prefix) }
                    .map { it.market }
                    .toList()
                getTickers(tickers, callback)
            }

            override fun onFailure(call: Call<List<UpBitTicker>>, t: Throwable) {
                callback.onDataNotAvailable(t.message)
            }
        })
    }

    private fun getTickers(market: List<String?>?, callback: UpBitDataSource.GetTickerCallback) {
        retrofit.getTicker(market).enqueue(object : Callback<List<UpBitTicker>> {
            override fun onResponse(call: Call<List<UpBitTicker>>, response: Response<List<UpBitTicker>>) {
                val responseTicker = response.body()
                if (responseTicker != null)
                    callback.onThickerLoaded(responseTicker)
                else
                    callback.onDataNotAvailable("Data is empty")
            }

            override fun onFailure(call: Call<List<UpBitTicker>>, t: Throwable) {
                callback.onDataNotAvailable(t.message)
            }

        })
    }

    private fun hasPrefix(prefix: String): Boolean = listOf("KRW", "BTC", "ETH", "USDT").contains(prefix)

    companion object {
        private lateinit var INSTANCE: UpBitRetrofitDataSource
        private var needsNewInstance = true

        @JvmStatic
        fun getInstance(): UpBitRetrofitDataSource {
            if (needsNewInstance) {
                INSTANCE = UpBitRetrofitDataSource()
                needsNewInstance = false
            }
            return INSTANCE
        }
    }
}