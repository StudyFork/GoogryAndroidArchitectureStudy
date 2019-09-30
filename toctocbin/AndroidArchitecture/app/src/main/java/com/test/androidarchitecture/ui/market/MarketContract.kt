package com.test.androidarchitecture.ui.market

import com.test.androidarchitecture.base.BaseContract
import com.test.androidarchitecture.data.MarketTitle

interface MarketContract {

    interface View : BaseContract.View<Presenter> {

        fun setViewpagerData(list: List<MarketTitle>)
    }

    interface Presenter : BaseContract.Presenter {

        fun getMarketAll()
    }
}