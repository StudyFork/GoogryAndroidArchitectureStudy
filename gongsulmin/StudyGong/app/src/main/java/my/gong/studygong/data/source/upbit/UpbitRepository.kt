package my.gong.studygong.data.source.upbit

import my.gong.studygong.data.model.response.UpbitMarketResponse
import my.gong.studygong.data.model.response.UpbitTickerResponse
import my.gong.studygong.data.network.RetrofitProvider
import retrofit2.Call
import retrofit2.Response

object  UpbitRepository
    :IUpbitDataSource{
    override fun getTickers(market: String ,success: (List<UpbitTickerResponse>) -> Unit, fail: (String) -> Unit) {
        RetrofitProvider.upbitRetrofit().getMarket().enqueue(object : retrofit2.Callback<List<UpbitMarketResponse>>{
            override fun onResponse(call: Call<List<UpbitMarketResponse>>, response: Response<List<UpbitMarketResponse>>) {
//                getMarket()
            }
            override fun onFailure(call: Call<List<UpbitMarketResponse>>, t: Throwable) {
                fail.invoke(" 마켓 데이터 실패 ")
            }
        })
    }

    override fun getMarket( success: (List<UpbitMarketResponse>) -> Unit, fail: (String) -> Unit) {
        RetrofitProvider.upbitRetrofit().getMarket().enqueue(object : retrofit2.Callback<List<UpbitMarketResponse>>{
            override fun onResponse(call: Call<List<UpbitMarketResponse>>, response: Response<List<UpbitMarketResponse>>) {
            }
            override fun onFailure(call: Call<List<UpbitMarketResponse>>, t: Throwable) {
                fail.invoke(" 마켓 데이터 실패 ")
            }
        })
    }
}