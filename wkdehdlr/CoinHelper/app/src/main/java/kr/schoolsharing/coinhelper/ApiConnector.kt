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

    private var krwList: MutableList<UpbitItem> = ArrayList()
    private var btcList: MutableList<UpbitItem> = ArrayList()
    private var ethList: MutableList<UpbitItem> = ArrayList()
    private var usdtList: MutableList<UpbitItem> = ArrayList()
    private var upbitMarketStr = ""

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
                if (response.body() != null) {
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
                if (response.body() != null) {
                    val list = response.body()

                    for (item in list!!) {

                        var category = item.market.split("-")[0]
                        var name = item.market.split("-")[1]

                        val doubleFormat = DecimalFormat("#.##")
                        val intFormat = DecimalFormat("#,###.##")

                        if (category == "KRW") {

                            krwList.add(
                                UpbitItem(
                                    name,
                                    intFormat.format(item.tradePrice),
                                    item.change,
                                    doubleFormat.format(item.signedChangeRate),
                                    doubleFormat.format(item.accTradePrice24h)
                                )
                            )

                        } else if (category == "BTC") {

                            btcList.add(
                                UpbitItem(
                                    name,
                                    intFormat.format(item.tradePrice),
                                    item.change,
                                    doubleFormat.format(item.signedChangeRate),
                                    doubleFormat.format(item.accTradePrice24h)
                                )
                            )

                        } else if (category == "ETH") {

                            ethList.add(
                                UpbitItem(
                                    name,
                                    intFormat.format(item.tradePrice),
                                    item.change,
                                    doubleFormat.format(item.signedChangeRate),
                                    doubleFormat.format(item.accTradePrice24h)
                                )
                            )

                        } else if (category == "USDT") {

                            usdtList.add(
                                UpbitItem(
                                    name,
                                    intFormat.format(item.tradePrice),
                                    item.change,
                                    doubleFormat.format(item.signedChangeRate),
                                    doubleFormat.format(item.accTradePrice24h)
                                )
                            )

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

    fun getKRWList() = this.krwList
    fun getBTCList() = this.btcList
    fun getETHList() = this.ethList
    fun getUSDTList() = this.usdtList
}