package com.namjackson.archstudy.view

import com.namjackson.archstudy.base.mvp.BasePresenter
import com.namjackson.archstudy.base.mvp.BaseView
import com.namjackson.archstudy.data.Ticker

interface CoinListContract {

    interface View : BaseView<Presenter> {
        fun showCoinList(ticker: List<Ticker>)

        fun showProgress()

        fun hideProgress()

        fun showError(errorMsg: String)
    }

    interface Presenter : BasePresenter {
        fun loadCoinList()
    }
}