package com.jskim5923.architecturestudy.coin

import com.jskim5923.architecturestudy.base.BasePresenter
import com.jskim5923.architecturestudy.base.BaseView
import com.jskim5923.architecturestudy.model.Ticker

interface CoinContract {
    interface View : BaseView<Presenter> {
        fun updateRecyclerView(tickerList: List<Ticker>)
    }

    interface Presenter : BasePresenter<View> {
        fun getTickerList(market: String?)
    }
}