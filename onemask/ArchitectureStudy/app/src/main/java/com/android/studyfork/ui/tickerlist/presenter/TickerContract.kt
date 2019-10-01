package com.android.studyfork.ui.tickerlist.presenter

import com.android.studyfork.base.BasePresenter
import com.android.studyfork.base.BaseView
import com.android.studyfork.network.model.Ticker

/**
 * created by onemask
 */
interface TickerContract {
    interface View : BaseView<Presenter>{
        fun setData(ticker: List<Ticker>)
    }

    interface Presenter : BasePresenter<View>{
        fun getTicker(market: String)
    }
}