package sample.nackun.com.studyfirst

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sample.nackun.com.studyfirst.market.Market
import sample.nackun.com.studyfirst.market.Ticker
import sample.nackun.com.studyfirst.network.Retrofit_interface

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var marketLike: String = ""

        val onClickListener = object : View.OnClickListener {
            override fun onClick(v: View?) {
                marketKRW.setTextColor(Color.BLACK)
                marketBTC.setTextColor(Color.BLACK)
                marketETH.setTextColor(Color.BLACK)
                marketUSDT.setTextColor(Color.BLACK)
                when(v){

                    marketKRW -> {
                        marketLike = "KRW"
                        marketKRW.setTextColor(Color.parseColor("#384786"))
                    }
                    marketBTC -> {
                        marketLike = "BTC"
                        marketBTC.setTextColor(Color.parseColor("#384786"))
                    }
                    marketETH -> {
                        marketLike = "ETH"
                        marketETH.setTextColor(Color.parseColor("#384786"))
                    }
                    marketUSDT -> {
                        marketLike = "USDT"
                        marketUSDT.setTextColor(Color.parseColor("#384786"))
                    }
                }
                initView(marketLike)
            }
        }

        marketKRW.setOnClickListener(onClickListener)
        marketBTC.setOnClickListener(onClickListener)
        marketETH.setOnClickListener(onClickListener)
        marketUSDT.setOnClickListener(onClickListener)

        marketKRW.callOnClick()

    }

    fun initView(marketLike: String): Unit{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.upbit.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(Retrofit_interface::class.java)

        service.requestMarket().enqueue(object : Callback<ArrayList<Market>> {
            override fun onFailure(call: Call<ArrayList<Market>>, t: Throwable) {
                Log.e("Retro", "fail_requestMarket")
            }

            override fun onResponse(call: Call<ArrayList<Market>>, response: Response<ArrayList<Market>>) {
                Log.e("Retro", "succ_requestMarket")
                if (response.body() != null) {
                    var markets: String = ""
                    for (i in 0..response.body()!!.size - 1) {
                        if (response.body()!!.get(i).market.startsWith(marketLike)) {
                            val marketA = response.body()!!.get(i).market
                            markets += "$marketA,"
                        }
                    }
                    markets = markets.substring(0, markets.length - 1)
                    Log.d("query", markets)

                    service.requestTicker(markets)
                        .enqueue(object : Callback<ArrayList<Ticker>> {
                            override fun onResponse(
                                call: Call<ArrayList<Ticker>>,
                                response: Response<ArrayList<Ticker>>
                            ) {
                                if (response.body() != null) {
                                    tickerRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                                    tickerRecyclerView.adapter = TickerAdapter(response.body()!!, this@MainActivity)
                                }
                            }

                            override fun onFailure(call: Call<ArrayList<Ticker>>, t: Throwable) {
                            }
                        })
                }
            }
        })
    }
}