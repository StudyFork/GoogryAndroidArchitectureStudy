package com.android.studyfork.ui.main.presenter

import com.android.studyfork.base.BaseContract


interface MainContract {
    interface View : BaseContract.View<Presenter> {
        fun setViewPagerData(marketData: Pair<List<String>, List<String>>)
    }

    interface Presenter : BaseContract.Presenter {
        fun loadData()
    }

}