package com.android.studyfork.ui.main.presenter

import com.android.studyfork.base.BasePresenter
import com.android.studyfork.base.BaseView


interface MainContract {
    interface View : BaseView<Presenter>{
        fun setViewPagerData(marketData: Pair<List<String>, List<String>>)
    }

    interface Presenter : BasePresenter<View> {
        fun loadData()
    }

}