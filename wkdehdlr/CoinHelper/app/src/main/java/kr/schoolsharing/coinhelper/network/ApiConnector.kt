package kr.schoolsharing.coinhelper.network

import android.util.Log
import kr.schoolsharing.coinhelper.model.UpbitItem
import kr.schoolsharing.coinhelper.model.UpbitMarket
import kr.schoolsharing.coinhelper.model.UpbitTicker
import kr.schoolsharing.coinhelper.util.TextEditor
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
                    setUpbitTicker(it.joinToString(",") { it.market })
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

                        val data = UpbitItem(
                            name,
                            TextEditor.makeTradePrice(item.tradePrice),
                            item.change,
                            TextEditor.makeSignedChangeRate(item.signedChangePrice),
                            TextEditor.makeAccTradePrice24h(item.accTradePrice24h)
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