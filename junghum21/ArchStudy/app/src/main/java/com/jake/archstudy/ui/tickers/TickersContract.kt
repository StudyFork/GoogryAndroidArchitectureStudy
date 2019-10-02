package com.jake.archstudy.ui.tickers

import com.jake.archstudy.base.BaseContract
import com.jake.archstudy.data.model.Ticker

interface TickersContract {

    interface View : BaseContract.View<Presenter> {

        fun setTickers(tickers: List<Ticker>)

    }

    interface Presenter : BaseContract.Presenter<View>

}