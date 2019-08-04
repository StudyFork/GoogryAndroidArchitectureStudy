package com.example.architecturestudy.ui

import com.example.architecturestudy.data.Coin

interface MarketContract {

    interface View {
        fun setTickerData(data: List<Coin>)

        fun clearTickerData()
    }

    interface Presenter {

        fun loadData(keyMarket: String?)

        fun onFailCallback(errorMsg: String)

    }

}