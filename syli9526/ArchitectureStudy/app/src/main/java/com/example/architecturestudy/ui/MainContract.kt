package com.example.architecturestudy.ui

import com.example.architecturestudy.network.model.MarketResponse

interface MainContract {

    interface View {

        fun setPage()

        fun setData(marketList: List<MarketResponse>)

        fun showMessage(message: String)

    }

    interface Presenter {

        fun getMarketList()
    }
}