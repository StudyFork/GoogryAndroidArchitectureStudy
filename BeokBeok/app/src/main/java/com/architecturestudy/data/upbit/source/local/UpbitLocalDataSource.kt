package com.architecturestudy.data.upbit.source.local

import com.architecturestudy.data.upbit.UpbitTicker
import com.architecturestudy.data.upbit.source.UpbitDataSource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UpbitLocalDataSource internal constructor(
    private val upbitTickerDao: UpbitTickerDao?
) : UpbitDataSource {

    override fun getMarketPrice(
        prefix: String,
        onSuccess: (List<UpbitTicker>) -> Unit,
        onFail: (Throwable) -> Unit
    ) {
        // NO OP
    }

    override fun saveTicker(upbitTicker: UpbitTicker) {
        CompositeDisposable().add(
            Observable.fromCallable {
                upbitTickerDao?.run {
                    insertTicker(upbitTicker)
                }
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        )
    }

    override fun sort(
        sortType: String,
        isDesc: Boolean,
        onSuccess: (List<UpbitTicker>) -> Unit,
        onFail: (Throwable) -> Unit
    ) {
        CompositeDisposable().add(
            Observable.fromCallable {
                when (sortType) {
                    "market" -> {
                        if (isDesc) {
                            upbitTickerDao?.sortMarketByDESC()
                        } else {
                            upbitTickerDao?.sortMarket()
                        }
                    }
                    "trade_price" -> {
                        if (isDesc) {
                            upbitTickerDao?.sortTradePriceByDESC()
                        } else {
                            upbitTickerDao?.sortTradePrice()
                        }
                    }
                    "signed_change_rate" -> {
                        if (isDesc) {
                            upbitTickerDao?.sortSignedChangeRateByDESC()
                        } else {
                            upbitTickerDao?.sortSignedChangeRate()
                        }

                    }
                    "acc_trade_price_24h" -> {
                        if (isDesc) {
                            upbitTickerDao?.sortAccTradePrice24hByDESC()
                        } else {
                            upbitTickerDao?.sortAccTradePrice24h()
                        }
                    }
                    else -> listOf()
                }
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.isNullOrEmpty()) {
                        onFail(IllegalStateException("Sort fail"))
                    } else {
                        onSuccess(it)
                    }
                }, {
                    onFail(IllegalStateException("Sort fail"))
                })
        )
    }

    companion object {
        private var instance: UpbitLocalDataSource? = null

        operator fun invoke(
            upbitTickerDao: UpbitTickerDao?
        ): UpbitLocalDataSource = instance ?: UpbitLocalDataSource(upbitTickerDao)
            .apply { instance = this }
    }
}