package com.jskim5923.architecturestudy.main

interface MainContract {

    interface View {
        fun updateViewpager(marketList: List<String>)
    }

    interface Presenter {
        fun loadMarketList()

        fun disposableClear()
    }
}