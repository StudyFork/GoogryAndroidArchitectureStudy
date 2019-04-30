package dev.daeyeon.gaasproject.data.source

import dev.daesin.gaasdy.data.MarketResponse
import dev.daesin.gaasdy.data.TickerResponse
import dev.daesin.gaasdy.network.NetworkManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpbitRepository : UpbitDataSource {

    var markets: String? = null

    override fun getTicker(success: (list: List<Ticker>) -> Unit?, fail: (msg: String) -> Unit?) {
        getMarkets(
                success = { result ->
                    markets = result
                    NetworkManager.instance?.getTicker(markets!!)!!
                            .enqueue(object : Callback<List<TickerResponse>?> {
                                override fun onFailure(call: Call<List<TickerResponse>?>,
                                                       t: Throwable) {
                                    fail.invoke(t.message!!)
                                }

                                override fun onResponse(call: Call<List<TickerResponse>?>,
                                                        response: Response<List<TickerResponse>?>) {
                                    success.invoke(response.body()!!.map { it.toTicker() })
                                }
                            })
                },
                fail = {
                    fail.invoke(it)
                }
        )
    }

    override fun getMarkets(success: (markets: String) -> Unit?, fail: (msg: String) -> Unit?) {
        if (markets == null) {
            NetworkManager.instance?.getMarketCode()!!
                    .enqueue(object : Callback<List<MarketResponse>?> {
                        override fun onFailure(call: Call<List<MarketResponse>?>,
                                               t: Throwable) {
                            fail.invoke(t.message!!)
                        }

                        override fun onResponse(call: Call<List<MarketResponse>?>,
                                                response: Response<List<MarketResponse>?>) {
                            success.invoke(response.body()!!.joinToString { it.market })
                        }
                    })
        } else {
            success.invoke(markets!!)
        }
    }

}