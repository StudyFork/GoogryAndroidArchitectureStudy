package com.example.architecturestudy.data.source.remote

import com.example.architecturestudy.data.CoinMarketResponse
import com.example.architecturestudy.data.CoinTickerResponse
import com.example.architecturestudy.data.source.CoinsDataSource
import com.example.architecturestudy.network.CoinApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoinsRemoteDataSource(
    val coinApiService: CoinApiService
) : CoinsDataSource {

    override fun getAllMarket(callback: CoinsDataSource.GetAllMarketCallback) {
        coinApiService
            .getAllCoinMarket()
            .enqueue(object : Callback<List<CoinMarketResponse>> {
                override fun onFailure(call: Call<List<CoinMarketResponse>>, t: Throwable) {
                    callback.onDataNotAvailable()
                }

                override fun onResponse(
                    call: Call<List<CoinMarketResponse>>,
                    response: Response<List<CoinMarketResponse>>
                ) {
                    response.body()?.let { callback.onAllMarketLoaded(it) } ?: callback.onDataNotAvailable()
                }
            })
    }

    override fun getCoinTickers(markets: String, callback: CoinsDataSource.GetCoinTickersCallback) {
        coinApiService
            .getCoinTickers(markets)
            .enqueue(object : Callback<List<CoinTickerResponse>> {
                override fun onFailure(call: Call<List<CoinTickerResponse>>, t: Throwable) {
                    callback.onDataNotAvailable()
                }

                override fun onResponse(
                    call: Call<List<CoinTickerResponse>>,
                    response: Response<List<CoinTickerResponse>>
                ) {
                    response.body()?.let { callback.onTickersLoaded(it) } ?: callback.onDataNotAvailable()
                }
            })
    }

    companion object {
        private var INSTANCE: CoinsRemoteDataSource? = null

        fun getInstance(coinApiService: CoinApiService): CoinsRemoteDataSource {
            return INSTANCE ?: CoinsRemoteDataSource(coinApiService).apply {
                INSTANCE = this
            }
        }

    }
}
