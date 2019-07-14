package sample.nackun.com.studyfirst.data.remote

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sample.nackun.com.studyfirst.data.DataSource
import sample.nackun.com.studyfirst.network.UpbitApi
import sample.nackun.com.studyfirst.vo.Market
import sample.nackun.com.studyfirst.vo.Ticker

class RemoteDataSource(private val retrofitService: UpbitApi) : DataSource {
    override fun requestMarkets(
        marketLike: String,
        onTickersLoaded: (List<Ticker>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        retrofitService.requestMarket().enqueue(object : Callback<List<Market>> {
            override fun onResponse(call: Call<List<Market>>, response: Response<List<Market>>) {
                val query =
                    response.body()?.let { data ->
                        data.filter { it.market.startsWith(marketLike) }
                            .joinToString { it.market }
                    } ?: ""
                Log.d("aa12", query)
                requestTickers(query, onTickersLoaded, onError)
            }

            override fun onFailure(call: Call<List<Market>>, t: Throwable) =
                onError(t)
        })
    }

    fun requestTickers(
        query: String,
        onTickersLoaded: (List<Ticker>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        retrofitService.requestTicker(query)
            .enqueue(object : Callback<List<Ticker>> {
                override fun onResponse(
                    call: Call<List<Ticker>>,
                    response: Response<List<Ticker>>
                ) = response.body()?.let(onTickersLoaded)
                    ?: onError(IllegalStateException("Tickers is Null"))

                override fun onFailure(call: Call<List<Ticker>>, t: Throwable) =
                    onError(t)
            })
    }
}