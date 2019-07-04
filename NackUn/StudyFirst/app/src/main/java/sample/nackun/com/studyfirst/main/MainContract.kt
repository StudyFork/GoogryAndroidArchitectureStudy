package sample.nackun.com.studyfirst.main

import sample.nackun.com.studyfirst.Base.BasePresenter
import sample.nackun.com.studyfirst.Base.BaseView

interface MainContract {
    interface View : BaseView<Presenter> {
        fun showTickers(tickers: List<Map<String, String>>)
        fun showMsg(msg: String?)
    }

    interface Presenter : BasePresenter {
        fun requestTickers(marketName: String)
    }
}