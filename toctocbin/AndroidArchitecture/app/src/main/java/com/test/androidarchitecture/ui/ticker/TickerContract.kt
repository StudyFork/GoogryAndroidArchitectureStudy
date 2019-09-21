package com.test.androidarchitecture.ui.ticker

import com.test.androidarchitecture.data.TickerFormat

interface TickerContract {

    interface View {

        fun setTickerData(list: List<TickerFormat>)

        fun showToast(msg: String)

    }

    interface Presenter {

        fun getTicker(marketSearch: String)

        fun disposablesClear()

    }
}