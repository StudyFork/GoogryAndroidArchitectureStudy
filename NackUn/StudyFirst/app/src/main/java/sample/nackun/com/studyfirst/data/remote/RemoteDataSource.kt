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

class RemoteDataSource(retrofitService: UpbitApi) : DataSource {
    private val retrofitService = retrofitService

    override fun requestMarkets(marketLike: String, callback: DataSource.RequestTickersCallback) {
        retrofitService.requestMarket().enqueue(object : Callback<ArrayList<Market>> {
            override fun onResponse(call: Call<ArrayList<Market>>, response: Response<ArrayList<Market>>) {
                val query =
                response.body()?.let { data ->
                    data.filter { it.market.startsWith(marketLike) }
                        .joinToString { it.market }
                }?:""
                Log.d("aa12", query)
                requestTickers(query, callback);
            }

            override fun onFailure(call: Call<ArrayList<Market>>, t: Throwable) =
                callback.onError("Don't request Markets")
        })
    }

    fun requestTickers(query: String, callback: DataSource.RequestTickersCallback) {
        retrofitService.requestTicker(query)
            .enqueue(object : Callback<ArrayList<Ticker>> {
                override fun onResponse(
                    call: Call<ArrayList<Ticker>>,
                    response: Response<ArrayList<Ticker>>
                ) = response.body()?.let(callback::onTickersLoaded)
                ?: callback.onError("Tickers is Null")

                override fun onFailure(call: Call<ArrayList<Ticker>>, t: Throwable) =
                    callback.onError("Don't request Tickers")
            })
    }
}