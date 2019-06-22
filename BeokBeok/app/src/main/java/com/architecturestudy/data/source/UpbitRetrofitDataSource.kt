package com.architecturestudy.data.source

import com.architecturestudy.data.UpbitTicker
import com.architecturestudy.data.base.Markets
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UpbitRetrofitDataSource private constructor() : UpbitDataSource {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.upbit.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(
            OkHttpClient.Builder().addInterceptor(
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            ).build()
        )
        .build()
        .create(UpbitService::class.java)

    override fun getMarketPrice(
        prefix: String,
        callback: UpbitDataSource.GetTickerCallback
    ) {
        retrofit.getMarkets()
            .enqueue(object : Callback<List<UpbitTicker>> {
                override fun onResponse(
                    call: Call<List<UpbitTicker>>,
                    response: Response<List<UpbitTicker>>
                ) {
                    val markets = response.body() ?: return
                    val tickers = markets
                        .asSequence()
                        .filter { enumValues<Markets>().any { it.markets == prefix } }
                        .filter { it.market!!.startsWith(prefix) }
                        .map { it.market }
                        .toList()
                    getTickers(tickers, callback)
                }

                override fun onFailure(
                    call: Call<List<UpbitTicker>>,
                    t: Throwable
                ) {
                    callback.onDataNotAvailable(t)
                }
            })
    }

    private fun getTickers(
        market: List<String?>?,
        callback: UpbitDataSource.GetTickerCallback
    ) {
        retrofit.getTicker(market)
            .enqueue(object : Callback<List<UpbitTicker>> {
                override fun onResponse(
                    call: Call<List<UpbitTicker>>,
                    response: Response<List<UpbitTicker>>
                ) {
                    val responseTicker = response.body()
                    if (responseTicker != null) {
                        callback.onTickerLoaded(responseTicker)
                    } else {
                        callback.onDataNotAvailable(
                            IllegalStateException("Data is empty")
                        )
                    }
                }

                override fun onFailure(
                    call: Call<List<UpbitTicker>>,
                    t: Throwable
                ) {
                    callback.onDataNotAvailable(t)
                }

            })
    }

    companion object {

        operator fun invoke(): UpbitRetrofitDataSource {
            return UpbitRetrofitDataSource()
        }

    }
}