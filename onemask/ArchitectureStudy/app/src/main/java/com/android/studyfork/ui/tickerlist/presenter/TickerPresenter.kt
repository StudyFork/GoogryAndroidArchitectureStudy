package com.android.studyfork.ui.tickerlist.presenter

import com.android.studyfork.network.model.Ticker
import com.android.studyfork.repository.UpbitRepositoryImpl
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import timber.log.Timber

/**
 * created by onemask
 */
class TickerPresenter(
    private val view: TickerContract.View
) : TickerContract.Presenter {

    private val disposables = CompositeDisposable()

    override fun getTicker(market: String) {

        val tickerList: ArrayList<Ticker> = arrayListOf()

        disposables += UpbitRepositoryImpl.getTickers(market)
            .subscribe({
                Timber.d("getTicker success")
                it.forEachIndexed { index, tickerResponse ->
                    tickerList.add(
                        index,
                        Ticker(
                            tickerResponse.market,
                            tickerResponse.tradePrice,
                            tickerResponse.signedChangeRate,
                            tickerResponse.accTradePrice24h
                        )
                    )
                }
                view.setData(tickerList)
            }, {
                Timber.e(it)
            })

    }

    override fun clearDisposable() {
        disposables.clear()
    }

}

