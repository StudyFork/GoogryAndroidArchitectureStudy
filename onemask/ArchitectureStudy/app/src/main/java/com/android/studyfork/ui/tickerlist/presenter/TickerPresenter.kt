package com.android.studyfork.ui.tickerlist.presenter

import android.annotation.SuppressLint
import com.android.studyfork.network.model.Ticker
import com.android.studyfork.repository.UpbitRepositoryImpl
import timber.log.Timber

/**
 * created by onemask
 */
class TickerPresenter(
    private val upbitRepository: UpbitRepositoryImpl,
    private val view: TickerContract.View
) : TickerContract.Presenter {

    @SuppressLint("CheckResult")
    override fun getTicker(market: String) {
        val tickerList: ArrayList<Ticker> = arrayListOf()
        upbitRepository.getTickers(market)
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
}

