package my.gong.studygong.data.source.upbit

import my.gong.studygong.data.model.Ticker
import my.gong.studygong.data.model.response.UpbitMarketResponse
import my.gong.studygong.data.model.response.UpbitTickerResponse
import my.gong.studygong.data.network.RetrofitProvider
import retrofit2.Call
import retrofit2.Response

object  UpbitRepository
    :IUpbitDataSource{
    override fun getTickers(success: (List<Ticker>) -> Unit, fail: (String) -> Unit) {
        getMarket(
            success = {
                    market ->
                RetrofitProvider.upbitRetrofit().getTicker(market).enqueue(object : retrofit2.Callback<List<UpbitTickerResponse>>{
                    override fun onResponse(call: Call<List<UpbitTickerResponse>>, response: Response<List<UpbitTickerResponse>>) {
                        success.invoke(
                            response.body()!!
                                .filter {
                                    it.market.split("-")[0] == "KRW"
                                }
                                .map {
                                    Ticker(
                                        it.market,
                                        String.format("%.0f", it.tradePrice),
                                        String.format("%.2f", it.changeRate * 100),
                                        String.format("%.5f", it.accTradePrice24h)
                                    )
                                }
                        )
                    }override fun onFailure(call: Call<List<UpbitTickerResponse>>, t: Throwable) {
                        fail.invoke(" 코인 데이터 통신 불가    ")
                    }
                })
            } ,
            fail = {
                fail.invoke(it)
            }
        )
    }

    private fun getMarket( success: (String) -> Unit  , fail: (String) -> Unit) {
        RetrofitProvider.upbitRetrofit().getMarket().enqueue(object : retrofit2.Callback<List<UpbitMarketResponse>>{
            override fun onResponse(call: Call<List<UpbitMarketResponse>>, response: Response<List<UpbitMarketResponse>>) {
                success.invoke(
                    response.body()!!.map {
                        it.market
                    }.joinToString (",")
                )
            }
            override fun onFailure(call: Call<List<UpbitMarketResponse>>, t: Throwable) {
                fail.invoke(" 마켓 데이터 통신 불가   ")
            }
        })
    }
}


