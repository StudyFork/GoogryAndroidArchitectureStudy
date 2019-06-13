package sample.nackun.com.studyfirst

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sample.nackun.com.studyfirst.market.Market
import sample.nackun.com.studyfirst.market.Ticker
import sample.nackun.com.studyfirst.network.UpbitApi

class MainActivity : AppCompatActivity() {

    private lateinit var retrofit: Retrofit
    private lateinit var service: UpbitApi
    private lateinit var tickerAdapter: TickerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAdapter()
        initRetrofit()
        setBtnClick()
        marketKRW.callOnClick()
    }

    fun initRetrofit(){
        retrofit = Retrofit.Builder()
            .baseUrl("https://api.upbit.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(UpbitApi::class.java)
    }

    fun setBtnClick(){
        val onClickListener = object : View.OnClickListener {
            override fun onClick(v: View?) {
                marketKRW.setTextColor(resources.getColor(R.color.grey))
                marketBTC.setTextColor(resources.getColor(R.color.grey))
                marketETH.setTextColor(resources.getColor(R.color.grey))
                marketUSDT.setTextColor(resources.getColor(R.color.grey))
                var selectedMarket = v as TextView
                selectedMarket.setTextColor(resources.getColor(R.color.indigo))
                initData(selectedMarket.text.toString())
            }
        }

        marketKRW.setOnClickListener(onClickListener)
        marketBTC.setOnClickListener(onClickListener)
        marketETH.setOnClickListener(onClickListener)
        marketUSDT.setOnClickListener(onClickListener)
    }

    fun initAdapter(){
        tickerAdapter = TickerAdapter()
        tickerRecyclerView.adapter = tickerAdapter
    }

    fun initData(marketLike: String) {

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
        })
    }
}