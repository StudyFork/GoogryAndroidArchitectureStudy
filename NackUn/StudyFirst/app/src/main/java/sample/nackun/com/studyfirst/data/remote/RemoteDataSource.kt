package sample.nackun.com.studyfirst.data.remote

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sample.nackun.com.studyfirst.data.DataSource
import sample.nackun.com.studyfirst.network.UpbitApi
import sample.nackun.com.studyfirst.vo.Market
import sample.nackun.com.studyfirst.vo.Ticker

class RemoteDataSource(private val retrofitService: UpbitApi) : DataSource {
    override fun requestMarkets(marketLike: String, callback: DataSource.RequestTickersCallback) {
        retrofitService.requestMarket().enqueue(object : Callback<List<Market>> {
            override fun onResponse(call: Call<List<Market>>, response: Response<List<Market>>) {
                val query =
                response.body()?.let { data ->
                    data.filter { it.market.startsWith(marketLike) }
                        .joinToString { it.market }
                }?:""
                Log.d("aa12", query)
                requestTickers(query, callback);
            }

            override fun onFailure(call: Call<List<Market>>, t: Throwable) =
                callback.onError(t)
        })
    }

    fun requestTickers(query: String, callback: DataSource.RequestTickersCallback) {
        retrofitService.requestTicker(query)
            .enqueue(object : Callback<List<Ticker>> {
                override fun onResponse(
                    call: Call<List<Ticker>>,
                    response: Response<List<Ticker>>
                ) = response.body()?.let(callback::onTickersLoaded)
                ?: callback.onError(IllegalStateException("Tickers is Null"))

                override fun onFailure(call: Call<List<Ticker>>, t: Throwable) =
                    callback.onError(t)
            })
    }
}