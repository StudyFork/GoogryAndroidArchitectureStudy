package com.studyfirstproject.data

import com.studyfirstproject.data.model.TickerModel
import com.studyfirstproject.network.RetrofitBuilder
import com.studyfirstproject.network.retrofitCallback

class CoinRepository : CoinDataSource {
    private val service = RetrofitBuilder.service
    private val loadErrMsg = "오류가 발생했습니다. 다시 시도해주세요."

    override fun getAllMarkets(callback: CoinDataSource.LoadMarketsCallback) {
        service.getAllMarket().enqueue(retrofitCallback { response, throwable ->
            response?.let {
                val body = response.body()
                if (!body.isNullOrEmpty()) {
                    val marketList = body
                        .asSequence()
                        .map { it.market }
                        .filter { it.startsWith("KRW-") }

                    val marketStr = marketList.joinToString(",")
                    callback.onMarketsLoaded(marketStr)
                } else {
                    callback.onDataNotAvailable(loadErrMsg, throwable?.message)
                }
            }

            throwable?.let {
                callback.onDataNotAvailable(loadErrMsg, throwable.message)
            }
        })
    }

    override fun getCoinData(markets: String, callback: CoinDataSource.LoadTickersCallback) {
        service.getTickers(markets).enqueue(retrofitCallback { response, throwable ->
            response?.let {
                val body = response.body()
                if (!body.isNullOrEmpty()) {
                    callback.onCoinsLoaded(body)
                } else {
                    callback.onDataNotAvailable(loadErrMsg, throwable?.message)
                }
            }

            throwable?.let {
                callback.onDataNotAvailable(loadErrMsg, throwable.message)
            }
        })
    }
}