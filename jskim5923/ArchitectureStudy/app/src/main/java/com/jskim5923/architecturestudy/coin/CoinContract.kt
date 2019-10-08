package com.jskim5923.architecturestudy.coin

import com.jskim5923.architecturestudy.base.BaseContract
import com.jskim5923.architecturestudy.model.Ticker

interface CoinContract {
    interface View : BaseContract.View {
        fun updateRecyclerView(tickerList: List<Ticker>)
    }

    interface Presenter : BaseContract.Presenter {
        fun getTickerList(market: String?)
    }
}