package sample.nackun.com.studyfirst.data.remote

import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sample.nackun.com.studyfirst.TickerAdapter
import sample.nackun.com.studyfirst.data.DataSource
import sample.nackun.com.studyfirst.network.UpbitApi
import sample.nackun.com.studyfirst.vo.Market
import sample.nackun.com.studyfirst.vo.Ticker

object RemoteDataSource : DataSource {
    private lateinit var retrofit: Retrofit
    private lateinit var service: UpbitApi
    private lateinit var tickerAdapter: TickerAdapter

    init {
        retrofit = Retrofit.Builder()
            .baseUrl("https://api.upbit.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(UpbitApi::class.java)
    }

    override fun requestMarkets(marketLike: String){
        service.requestMarket().enqueue(object : Callback<ArrayList<Market>> {
            override fun onFailure(call: Call<ArrayList<Market>>, t: Throwable) {
            }

            override fun onResponse(call: Call<ArrayList<Market>>, response: Response<ArrayList<Market>>) {
                lateinit var query: String
                response.body()?.let {
                    query =
                        it.filter { item ->
                            item.market.startsWith(marketLike)
                        }
                            .joinToString { marketModel ->
                                marketModel.market
                            }
                }
                Log.d("aa12", query)
                requestTickers(query);
            }
        })
    }

    override fun requestTickers(query: String){
        service.requestTicker(query)
            .enqueue(object : Callback<ArrayList<Ticker>> {
                override fun onResponse(
                    call: Call<ArrayList<Ticker>>,
                    response: Response<ArrayList<Ticker>>
                ) {
                    response.body()?.let { tickerAdapter.setItems(it) }
                }

                override fun onFailure(call: Call<ArrayList<Ticker>>, t: Throwable) {
                }
            })
    }

    /*
    fun initAdapter() {
        tickerAdapter = TickerAdapter()
        tickerRecyclerView.adapter = tickerAdapter
    }
    */
}