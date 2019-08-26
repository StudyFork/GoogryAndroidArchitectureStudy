package com.jake.archstudy.data.source

import com.jake.archstudy.network.response.MarketResponse
import com.jake.archstudy.network.response.TickerResponse
import com.jake.archstudy.network.service.UpbitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpbitRemoteDataSource(private val upbitService: UpbitService) : UpbitDataSource {

    override fun getMarketAll(
        success: (List<MarketResponse>) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        upbitService.getMarketAll()
            .enqueue(object : Callback<List<MarketResponse>?> {
                override fun onFailure(
                    call: Call<List<MarketResponse>?>,
                    t: Throwable
                ) {
                    t.printStackTrace()
                    failure(t)
                }

                override fun onResponse(
                    call: Call<List<MarketResponse>?>,
                    response: Response<List<MarketResponse>?>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { success(it) }
                    }
                }
            })
    }

    override fun getTicker(
        markets: String,
        success: (List<TickerResponse>) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        upbitService.getTicker(markets)
            .enqueue(object : Callback<List<TickerResponse>?> {
                override fun onFailure(
                    call: Call<List<TickerResponse>?>,
                    t: Throwable
                ) {
                    t.printStackTrace()
                    failure(t)
                }

                override fun onResponse(
                    call: Call<List<TickerResponse>?>,
                    response: Response<List<TickerResponse>?>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { success(it) }
                    }
                }
            })
    }

}