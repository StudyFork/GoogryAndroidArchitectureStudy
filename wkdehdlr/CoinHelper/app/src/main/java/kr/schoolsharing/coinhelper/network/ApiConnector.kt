package kr.schoolsharing.coinhelper.network

import android.util.Log
import kr.schoolsharing.coinhelper.data.UpbitItem
import kr.schoolsharing.coinhelper.data.UpbitMarket
import kr.schoolsharing.coinhelper.data.UpbitTicker
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConnector {

    val krwList: MutableList<UpbitItem> = ArrayList()
    val btcList: MutableList<UpbitItem> = ArrayList()
    var ethList: MutableList<UpbitItem> = ArrayList()
    val usdtList: MutableList<UpbitItem> = ArrayList()
    var upbitMarketStr = ""

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
//                    list.joinToString(",")
                    upbitMarketStr = upbitMarketStr.substring(0, upbitMarketStr.length - 1)
//                    list.joinToString(","){it.market}
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

                        val category = item.market.split("-")[0]
                        val name = item.market.split("-")[1]
                        val price = when {
                            item.tradePrice.toInt() > 0 -> String.format("%,d", item.tradePrice.toInt())
                            else -> String.format("%,f", item.tradePrice)
                        }

                        val data = UpbitItem(
                            name,
                            price,
                            item.change,
                            String.format("%.2f%%", item.signedChangeRate * 100),
                            String.format("%,d", item.accTradePrice24h.toInt())
                        )

                        when (category) {
                            "KRW" -> krwList.add(data)
                            "BTC" -> btcList.add(data)
                            "ETH" -> ethList.add(data)
                            else -> usdtList.add(data)
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