package sample.nackun.com.studyfirst

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
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

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.upbit.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            //.client(OkHttpClient())
            .build()

        val service = retrofit.create(Retrofit_interface::class.java)


        //val call: Call<List<Market>> = service.requestMarket()
        var marketsKRW: String = ""

        service.requestMarket().enqueue(object : Callback<ArrayList<Market>> {
            override fun onFailure(call: Call<ArrayList<Market>>, t: Throwable) {
                Toast.makeText(applicationContext, "실패", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ArrayList<Market>>, response: Response<ArrayList<Market>>) {
                if (response.body() != null) {
                    var markets: String = ""
                    for (i in 0..response.body()!!.size - 1) {
                        if(response.body()!!.get(i).market.startsWith("KRW")) {
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
                                        Toast.makeText(applicationContext, "성공", Toast.LENGTH_SHORT).show()
                                        for (i in 0..response.body()!!.size - 1) {
                                            val marketB = response.body()!!.get(i).toString()
                                            //val sb = StringBuilder().append(tvTemp.text).append(marketB).append("\n")
                                            tvTemp.text = marketB
                                        }
                                    }
                                }

                                override fun onFailure(call: Call<ArrayList<Ticker>>, t: Throwable) {
                                    Toast.makeText(applicationContext, "실패", Toast.LENGTH_SHORT).show()
                                }
                            })
                    }
                    Toast.makeText(applicationContext, "끝", Toast.LENGTH_SHORT).show()
                }
        })
    }
}