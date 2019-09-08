package com.android.studyfork.ui.tickerlist.presenter

import com.android.studyfork.network.model.Ticker

/**
 * created by onemask
 */
interface TickerContract {
    interface View {
        fun setRecyclerView()
        fun setData(ticker: List<Ticker>)
    }

    interface Presenter {
        fun getTicker(market: String)
    }
}