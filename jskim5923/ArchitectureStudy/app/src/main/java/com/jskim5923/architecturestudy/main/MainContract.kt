package com.jskim5923.architecturestudy.main

import com.jskim5923.architecturestudy.base.BasePresenter
import com.jskim5923.architecturestudy.base.BaseView

interface MainContract {

    interface View : BaseView<Presenter> {
        fun updateViewpager(marketList: List<String>)
    }

    interface Presenter : BasePresenter<View> {
        fun loadMarketList()
    }
}