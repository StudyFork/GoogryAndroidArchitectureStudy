package sample.nackun.com.studyfirst.main

import sample.nackun.com.studyfirst.BasePresenter
import sample.nackun.com.studyfirst.BaseView
import sample.nackun.com.studyfirst.vo.Ticker

interface MainContract {
    interface View : BaseView<Presenter> {
        fun showTickes(tickers: ArrayList<Ticker>)
        fun showToast(str: String?)
    }

    interface Presenter : BasePresenter {
        fun requestTickers(marketName: String)
    }
}