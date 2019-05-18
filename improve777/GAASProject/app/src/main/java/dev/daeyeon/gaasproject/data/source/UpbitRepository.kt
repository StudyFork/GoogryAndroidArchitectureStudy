package dev.daeyeon.gaasproject.data.source

import dev.daeyeon.gaasproject.data.Ticker
import dev.daeyeon.gaasproject.data.response.MarketResponse
import dev.daeyeon.gaasproject.data.response.ResponseCode
import dev.daeyeon.gaasproject.data.response.TickerResponse
import dev.daeyeon.gaasproject.network.UpbitApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpbitRepository(private val upbitApi: UpbitApi) : UpbitDataSource {

    var markets = ""

    private var tickerCall: Call<List<TickerResponse>>? = null
    private var marketCall: Call<List<MarketResponse>>? = null

    override fun getTicker(
        baseCurrency: String,
        success: (List<Ticker>) -> Unit,
        fail: (String) -> Unit
    ) {
        getMarkets(
            success = { result ->
                markets = result
                tickerCall = upbitApi.getTicker(markets)

                tickerCall?.enqueue(object : Callback<List<TickerResponse>?> {
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
                                success.invoke(
                                    list.filter { it.market.startsWith(baseCurrency) }
                                        .map {
                                            it.toTicker()
                                        })
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
            marketCall = upbitApi.getMarketCode()

            marketCall?.enqueue(object : Callback<List<MarketResponse>?> {
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
                            success.invoke(list.joinToString(separator = ",") { it.market })
                        }
                    } ?: fail.invoke(ResponseCode.CODE_NULL_SUCCESS)
                }
            })
        } else {
            success.invoke(markets)
        }
    }

    override fun cancelMarketCall() {
        marketCall?.cancel()
    }

    override fun cancelTickerCall() {
        tickerCall?.cancel()
    }
}