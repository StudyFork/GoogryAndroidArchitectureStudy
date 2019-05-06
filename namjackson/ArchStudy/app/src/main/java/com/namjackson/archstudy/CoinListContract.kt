package com.namjackson.archstudy

import com.namjackson.archstudy.base.mvp.BasePresenter
import com.namjackson.archstudy.base.mvp.BaseView
import com.namjackson.archstudy.data.Ticker

interface CoinListContract {

    interface View : BaseView<Presenter> {
        fun showCoinList(ticker: List<Ticker>)

        fun setProgress(boolean: Boolean)

        fun showError(errorMsg: String)
    }

    interface Presenter : BasePresenter {
        var baseCurrency: String

        fun loadCoinList()
    }
}