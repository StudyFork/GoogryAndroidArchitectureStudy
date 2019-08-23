package com.example.mystudy.ui

import com.example.mystudy.data.FormatTickers

interface UpbitContract {
    interface View : BaseView<Presenter> {
        fun showUpbitTickerList(tickerList: List<FormatTickers>)
        fun showFailedUpbitTickerList()
    }

    interface Presenter : BasePresenter {
        fun getTicker(firstMarket: String?)
    }
}