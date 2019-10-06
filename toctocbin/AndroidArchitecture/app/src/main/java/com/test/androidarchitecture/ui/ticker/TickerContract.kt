package com.test.androidarchitecture.ui.ticker

import com.test.androidarchitecture.base.BaseContract
import com.test.androidarchitecture.data.TickerFormat

interface TickerContract {

    interface View : BaseContract.View<Presenter>{

        fun setTickerData(list: List<TickerFormat>)
    }

    interface Presenter : BaseContract.Presenter {

        fun getTicker()
    }
}