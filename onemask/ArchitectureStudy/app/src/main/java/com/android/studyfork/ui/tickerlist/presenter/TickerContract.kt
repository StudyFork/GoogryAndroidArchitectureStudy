package com.android.studyfork.ui.tickerlist.presenter

import com.android.studyfork.base.BaseContract
import com.android.studyfork.network.model.Ticker

/**
 * created by onemask
 */
interface TickerContract {
    interface View : BaseContract.View<Presenter> {
        fun setData(ticker: List<Ticker>)
    }

    interface Presenter : BaseContract.Presenter {
        fun getTicker(market: String)
    }
}