package com.example.mystudy.ui

import com.example.mystudy.data.FormatTickers

interface UpbitContract {
    interface View : BaseView<Presenter> {
        fun showSuccessUpbitTickerList(tickerList: MutableList<FormatTickers>)
        fun showFailedUpbitTickerList()

    }

    interface Presenter : BasePresenter {
        fun getTicker(firstMarket: String)
    }
}