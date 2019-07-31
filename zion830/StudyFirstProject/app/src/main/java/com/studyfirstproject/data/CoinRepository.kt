package com.studyfirstproject.data

import com.studyfirstproject.data.model.TickerModel
import com.studyfirstproject.network.CoinApi
import com.studyfirstproject.network.retrofitCallback

class CoinRepository(private val service: CoinApi) : CoinDataSource {
    private val loadErrMsg = "오류가 발생했습니다. 다시 시도해주세요."

    override fun getAllMarkets(
        success: (String) -> Unit,
        failed: (String, String?) -> Unit
    ) {
        service.getAllMarket().enqueue(retrofitCallback { response, throwable ->
            response?.let {
                val body = response.body()
                if (!body.isNullOrEmpty()) {
                    val marketList = body
                        .asSequence()
                        .map { it.market }
                        .filter { it.startsWith("KRW-") }

                    val marketStr = marketList.joinToString(",")
                    marketStr.run(success)
                } else {
                    failed(loadErrMsg, throwable?.message)
                }
            }

            throwable?.let {
                failed(loadErrMsg, throwable.message)
            }
        })
    }

    override fun getCoinData(
        markets: String,
        success: (List<TickerModel>) -> Unit,
        failed: (String, String?) -> Unit
    ) {
        service.getTickers(markets).enqueue(retrofitCallback { response, throwable ->
            response?.let {
                val body = response.body()
                if (!body.isNullOrEmpty()) {
                    body.run(success)
                } else {
                    failed(loadErrMsg, throwable?.message)
                }
            }

            throwable?.let {
                failed(loadErrMsg, throwable.message)
            }
        })
    }
}