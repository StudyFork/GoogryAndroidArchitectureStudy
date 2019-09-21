package com.android.studyfork.ui.main.presenter

import com.android.studyfork.network.model.MarketResponse


interface MainContract {
    interface View {
        fun setViewPagerData(marketData: Map<String, List<MarketResponse>>)
    }

    interface Presenter {
        fun loadData()
        fun clearDisposable()
    }

}