package sample.nackun.com.studyfirst.main

import sample.nackun.com.studyfirst.BasePresenter
import sample.nackun.com.studyfirst.BaseView
import sample.nackun.com.studyfirst.vo.Market
import sample.nackun.com.studyfirst.vo.Ticker

interface MainContract {
    interface View : BaseView<Presenter>{
        var marketName: String
        fun showTickes(tickers: ArrayList<Ticker>)
    }

    interface Presenter : BasePresenter {
        fun requestTickers()
    }
}