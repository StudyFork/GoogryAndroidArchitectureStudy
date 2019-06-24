package com.architecturestudy.data.upbit.source

import com.architecturestudy.data.common.MarketTypes
import com.architecturestudy.data.upbit.service.UpbitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UpbitRetrofitDataSource private constructor(
    private val retrofit: UpbitService
) : UpbitDataSource {

    override fun getMarketPrice(
        prefix: String,
        callback: UpbitDataSource.GetTickerCallback
    ) {
        CompositeDisposable().add(
            retrofit.getMarkets()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val markets = it.body() ?: return@subscribe
                    val tickers = markets
                        .asSequence()
                        .filter {
                            enumValues<MarketTypes>().any { data ->
                                data.markets == prefix
                            }
                        }
                        .filter { data -> data.market!!.startsWith(prefix) }
                        .map { data -> data.market }
                        .toList()
                    getTickers(tickers, callback)
                }, {
                    callback.onDataNotAvailable(it)
                })
        )
    }

    private fun getTickers(
        market: List<String?>?,
        callback: UpbitDataSource.GetTickerCallback
    ) {
        CompositeDisposable().add(
            retrofit.getTicker(market)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val responseTicker = it.body()
                    if (responseTicker != null) {
                        callback.onTickerLoaded(responseTicker)
                    } else {
                        callback.onDataNotAvailable(
                            IllegalStateException("Data is empty")
                        )
                    }
                }, {
                    callback.onDataNotAvailable(it)
                })
        )
    }

    companion object {
        private var instance: UpbitRetrofitDataSource? = null

        operator fun invoke(
            retrofit: UpbitService
        ): UpbitRetrofitDataSource {
            return instance ?: UpbitRetrofitDataSource(retrofit)
                .apply { instance = this }
        }

    }
}