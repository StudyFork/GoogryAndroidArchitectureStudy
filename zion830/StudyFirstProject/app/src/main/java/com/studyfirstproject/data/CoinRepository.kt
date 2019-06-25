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
                if (!response.body().isNullOrEmpty()) {
                    val marketList = response.body()!!
                        .asSequence()
                        .map { it.market }
                        .filter { it.substring(0, it.indexOf("-")) == "KRW" }

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
                if (!response.body().isNullOrEmpty()) {
                    val coinData = response.body() as List<TickerModel>
                    callback.onCoinsLoaded(coinData)
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