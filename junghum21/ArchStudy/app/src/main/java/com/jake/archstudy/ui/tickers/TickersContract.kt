package com.jake.archstudy.ui.tickers

import com.jake.archstudy.base.BasePresenter
import com.jake.archstudy.base.BaseView
import com.jake.archstudy.data.model.Ticker

interface TickersContract {

    interface View : BaseView<Presenter> {

        fun setTickers(tickers: List<Ticker>)

    }

    interface Presenter : BasePresenter<View>

}