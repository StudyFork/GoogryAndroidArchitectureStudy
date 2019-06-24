package sample.nackun.com.studyfirst.main

import sample.nackun.com.studyfirst.Base.BasePresenter
import sample.nackun.com.studyfirst.Base.BaseView
import sample.nackun.com.studyfirst.vo.Ticker

interface MainContract {
    interface View : BaseView<Presenter> {
        fun showTickers(tickers: ArrayList<Ticker>)
    }

    interface Presenter : BasePresenter {
        fun requestTickers(marketName: String)
    }
}