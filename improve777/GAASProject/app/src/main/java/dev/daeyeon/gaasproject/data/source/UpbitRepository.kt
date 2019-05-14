package dev.daeyeon.gaasproject.data.source

import dev.daeyeon.gaasproject.data.MarketResponse
import dev.daeyeon.gaasproject.data.ResponseCode
import dev.daeyeon.gaasproject.data.TickerResponse
import dev.daeyeon.gaasproject.network.NetworkManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpbitRepository : UpbitDataSource {

    private var markets = ""

    override fun getTicker(success: (list: List<Ticker>) -> Unit, fail: (msg: String) -> Unit) {
        getMarkets(
            success = { result ->
                markets = result
                NetworkManager.instance.getTicker(markets)
                    .enqueue(object : Callback<List<TickerResponse>?> {
                        override fun onFailure(
                            call: Call<List<TickerResponse>?>,
                            t: Throwable
                        ) {
                            fail.invoke(t.message ?: ResponseCode.CODE_NULL_FAIL_MSG)
                        }

                        override fun onResponse(
                            call: Call<List<TickerResponse>?>,
                            response: Response<List<TickerResponse>?>
                        ) {
                            response.body()?.let { list ->
                                if (list.isEmpty()) {
                                    fail.invoke(ResponseCode.CODE_EMPTY_SUCCESS)
                                } else {
                                    success.invoke(list.map { it.toTicker() })
                                }
                            } ?: fail.invoke(ResponseCode.CODE_NULL_SUCCESS)
                        }
                    })
            },
            fail = {
                fail.invoke(it)
            }
        )
    }

    override fun getMarkets(success: (markets: String) -> Unit, fail: (msg: String) -> Unit) {
        if (markets.isEmpty()) {
            NetworkManager.instance.getMarketCode()
                .enqueue(object : Callback<List<MarketResponse>?> {
                    override fun onFailure(
                        call: Call<List<MarketResponse>?>,
                        t: Throwable
                    ) {
                        fail.invoke(t.message ?: ResponseCode.CODE_NULL_FAIL_MSG)
                    }

                    override fun onResponse(
                        call: Call<List<MarketResponse>?>,
                        response: Response<List<MarketResponse>?>
                    ) {
                        response.body()?.let { list ->
                            if (list.isEmpty()) {
                                fail.invoke(ResponseCode.CODE_EMPTY_SUCCESS)
                            } else {
                                success.invoke(list.joinToString { it.market })
                            }
                        } ?: fail.invoke(ResponseCode.CODE_NULL_SUCCESS)
                    }
                })
        } else {
            success.invoke(markets)
        }
    }
}