package com.example.architecturestudy.data.source.remote

import com.example.architecturestudy.data.CoinMarketResponse
import com.example.architecturestudy.data.CoinTickerResponse
import com.example.architecturestudy.data.source.CoinsDataSource
import com.example.architecturestudy.network.CoinApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CoinsRemoteDataSource : CoinsDataSource {
    private const val API_URL = "https://api.upbit.com/"

    private val retrofit: Retrofit
    private val coinApiService: CoinApiService

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        coinApiService = retrofit.create(CoinApiService::class.java)
    }

    override fun getAllMarket(callback: CoinsDataSource.GetAllMarketCallback) {
        coinApiService
            .getAllCoinMarket()
            .enqueue(object : Callback<List<CoinMarketResponse>> {
                override fun onFailure(call: Call<List<CoinMarketResponse>>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<List<CoinMarketResponse>>,
                    response: Response<List<CoinMarketResponse>>
                ) {
                    response.body()?.let { callback.onAllMarketLoaded(it) } // 이부분 맞게 코딩한건지 확인 필요
                }
            })
    }

    override fun getCoinTicker(coin: String, callback: CoinsDataSource.GetCoinTickerCallback) {
        coinApiService
            .getCurrTicker(coin)
            .enqueue(object : Callback<List<CoinTickerResponse>> {
                override fun onFailure(call: Call<List<CoinTickerResponse>>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<List<CoinTickerResponse>>,
                    response: Response<List<CoinTickerResponse>>
                ) {
                    response.body()?.let { callback.onTickerLoaded(it) }
                }
            })
    }

}
