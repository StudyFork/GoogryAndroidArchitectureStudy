package com.test.androidarchitecture.ui.market

import com.test.androidarchitecture.data.MarketTitle

interface MarketContract {

    interface View {

        fun setViewpagerData(list: List<MarketTitle>)

        fun showToast(msg: String)

    }

    interface Presenter {

        fun getMarketAll()

        fun disposablesClear()

    }
}