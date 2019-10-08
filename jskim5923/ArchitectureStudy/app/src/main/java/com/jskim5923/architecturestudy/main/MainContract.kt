package com.jskim5923.architecturestudy.main

import com.jskim5923.architecturestudy.base.BaseContract

interface MainContract {

    interface View : BaseContract.View {
        fun updateViewpager(marketList: List<String>)
    }

    interface Presenter : BaseContract.Presenter {
        fun loadMarketList()
    }
}