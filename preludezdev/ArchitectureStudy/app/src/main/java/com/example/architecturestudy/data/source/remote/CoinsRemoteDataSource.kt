package com.example.architecturestudy.data.source.remote

import com.example.architecturestudy.data.CoinMarketResponse
import com.example.architecturestudy.data.CoinTickerResponse
import com.example.architecturestudy.data.source.CoinsDataSource
import com.example.architecturestudy.network.CoinApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoinsRemoteDataSource(
    private val coinApiService: CoinApiService
) : CoinsDataSource {

    override fun getAllMarket(
        onSuccess: (data: List<CoinMarketResponse>?) -> Unit,
        onFail: (t: Throwable) -> Unit
    ) {
        coinApiService
            .getAllCoinMarket()
            .enqueue(object : Callback<List<CoinMarketResponse>> {
                override fun onFailure(call: Call<List<CoinMarketResponse>>, t: Throwable) {
                    onFail(IllegalStateException("데이터를 요청에 실패했습니다"))
                }

                override fun onResponse(
                    call: Call<List<CoinMarketResponse>>,
                    response: Response<List<CoinMarketResponse>>
                ) {
                    onSuccess(response.body())
                }
            })
    }

    override fun getCoinTickers(
        markets: String,
        onSuccess: (List<CoinTickerResponse>?) -> Unit,
        onFail: (t: Throwable) -> Unit
    ) {
        coinApiService
            .getCoinTickers(markets)
            .enqueue(object : Callback<List<CoinTickerResponse>> {
                override fun onFailure(call: Call<List<CoinTickerResponse>>, t: Throwable) {
                    onFail(IllegalStateException("데이터를 요청에 실패했습니다"))
                }

                override fun onResponse(
                    call: Call<List<CoinTickerResponse>>,
                    response: Response<List<CoinTickerResponse>>
                ) {
                    onSuccess(response.body())
                }
            })
    }

    companion object {
        private var INSTANCE: CoinsRemoteDataSource? = null

        fun getInstance(coinApiService: CoinApiService): CoinsRemoteDataSource {
            return INSTANCE ?: CoinsRemoteDataSource(coinApiService).apply { INSTANCE = this }
        }
    }

}
