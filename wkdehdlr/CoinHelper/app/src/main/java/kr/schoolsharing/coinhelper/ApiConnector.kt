package kr.schoolsharing.coinhelper

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.DecimalFormat

class ApiConnector {

    var krwList: MutableList<UpbitItem> = ArrayList()
        private set

    var btcList: MutableList<UpbitItem> = ArrayList()
        private set

    var ethList: MutableList<UpbitItem> = ArrayList()
        private set

    var usdtList: MutableList<UpbitItem> = ArrayList()
        private set

    var upbitMarketStr = ""
        private set

    fun setUpbitMarket() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.upbit.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(createOkHttpClient())
            .build()


        val service = retrofit.create(UpbitService::class.java)
        val call: Call<List<UpbitMarket>> = service.getUpBitMarket()

        call.enqueue(object : Callback<List<UpbitMarket>> {
            override fun onFailure(call: Call<List<UpbitMarket>>, t: Throwable) {
                Log.e("error: ", t.message)
            }

            override fun onResponse(call: Call<List<UpbitMarket>>, response: Response<List<UpbitMarket>>) {
                response.body()?.let {
                    val list = response.body()
                    for (item in list!!) {
//                        setUpbitTicker(item.market)
                        upbitMarketStr += (item.market + ",")
                    }
                    upbitMarketStr = upbitMarketStr.substring(0, upbitMarketStr.length - 1)
                    setUpbitTicker(upbitMarketStr)
                    Log.d("success :: ", upbitMarketStr)
                }
            }
        })
    }

    fun setUpbitTicker(market: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.upbit.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(createOkHttpClient())
            .build()


        val service = retrofit.create(UpbitService::class.java)
        val call: Call<List<UpbitTicker>> = service.getUpBitTicker(market)

        call.enqueue(object : Callback<List<UpbitTicker>> {
            override fun onFailure(call: Call<List<UpbitTicker>>, t: Throwable) {
                Log.e("error: ", t.message)
            }


            override fun onResponse(call: Call<List<UpbitTicker>>, response: Response<List<UpbitTicker>>) {
                response.body()?.let {
                    val list = response.body()

                    for (item in list!!) {

                        var category = item.market.split("-")[0]
                        var name = item.market.split("-")[1]

                        val doubleFormat = DecimalFormat("#.##")
                        val intFormat = DecimalFormat("#,###.##")
                        val data = UpbitItem(
                            name,
                            intFormat.format(item.tradePrice),
                            item.change,
                            doubleFormat.format(item.signedChangeRate),
                            doubleFormat.format(item.accTradePrice24h)
                        )

                        when (category) {
                            "KRW" -> krwList.add(data)
                            "BTC" -> krwList.add(data)
                            "ETH" -> krwList.add(data)
                            else -> krwList.add(data)
                        }
                    }
                }
            }
        })
    }

    private fun createOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(interceptor)
        return builder.build()
    }
}