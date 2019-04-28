package my.gong.studygong.data.source.upbit

import my.gong.studygong.data.model.response.UpbitMarketResponse
import my.gong.studygong.data.model.response.UpbitTickerResponse
import my.gong.studygong.data.network.RetrofitProvider
import retrofit2.Call
import retrofit2.Response

object  UpbitRepository
    :IUpbitDataSource{
    override fun getTickers(success: (List<UpbitTickerResponse>) -> Unit, fail: (String) -> Unit) {
        getMarket(
            success = {
                    market ->
                RetrofitProvider.upbitRetrofit().getTicker(market).enqueue(object : retrofit2.Callback<List<UpbitTickerResponse>>{
                    override fun onResponse(call: Call<List<UpbitTickerResponse>>, response: Response<List<UpbitTickerResponse>>) {
                        success.invoke(response.body()!!)
                    }
                    override fun onFailure(call: Call<List<UpbitTickerResponse>>, t: Throwable) {
                        fail.invoke(" ${t.cause}")
                    }
                })
            }
        )

    }

    private fun getMarket( success: (String) -> Unit  ) {
        RetrofitProvider.upbitRetrofit().getMarket().enqueue(object : retrofit2.Callback<List<UpbitMarketResponse>>{
            override fun onResponse(call: Call<List<UpbitMarketResponse>>, response: Response<List<UpbitMarketResponse>>) {
                success.invoke(
                    response.body()!!.map {
                        it.market
                    }.joinToString (",")
                )
            }
            override fun onFailure(call: Call<List<UpbitMarketResponse>>, t: Throwable) {
//                fail.invoke("    데이터 전 송  실 패    ")
            }
        })
    }
}


