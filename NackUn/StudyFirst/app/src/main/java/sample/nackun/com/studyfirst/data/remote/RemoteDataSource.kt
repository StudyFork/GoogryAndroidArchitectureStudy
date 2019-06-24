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

class RemoteDataSource : DataSource {
    private val retrofitService: UpbitApi = Retrofit.Builder()
        .baseUrl("https://api.upbit.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UpbitApi::class.java)

    override fun requestMarkets(marketLike: String, callback: DataSource.RequestTickersCallback) {
        retrofitService.requestMarket().enqueue(object : Callback<ArrayList<Market>> {
            override fun onFailure(call: Call<ArrayList<Market>>, t: Throwable) {
                callback.onError("Don't request Markets")
            }

            override fun onResponse(call: Call<ArrayList<Market>>, response: Response<ArrayList<Market>>) {
                var query: String = ""
                response.body()?.let {
                    query = it.filter { it.market.startsWith(marketLike) }
                        .joinToString { it.market }
                }
                Log.d("aa12", query)
                requestTickers(query, callback);
            }
        })
    }

    fun requestTickers(query: String, callback: DataSource.RequestTickersCallback) {
        retrofitService.requestTicker(query)
            .enqueue(object : Callback<ArrayList<Ticker>> {
                override fun onResponse(
                    call: Call<ArrayList<Ticker>>,
                    response: Response<ArrayList<Ticker>>
                ) {
                    response.body()?.let {
                        callback.onTickersLoaded(it)
                    } ?: callback.onError("Tickers is Null")
                }

                override fun onFailure(call: Call<ArrayList<Ticker>>, t: Throwable) {
                    callback.onError("Don't request Tickers")
                }
            })
    }
}