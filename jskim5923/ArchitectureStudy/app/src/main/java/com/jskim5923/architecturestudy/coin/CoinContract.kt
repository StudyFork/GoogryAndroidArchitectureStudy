package com.jskim5923.architecturestudy.coin

import com.jskim5923.architecturestudy.model.Ticker

interface CoinContract {
    interface View {
        fun updateRecyclerView(tickerList: List<Ticker>)
    }

    interface Presenter {
        fun getTickerList(market: String?)

        fun disposableClear()
    }
}