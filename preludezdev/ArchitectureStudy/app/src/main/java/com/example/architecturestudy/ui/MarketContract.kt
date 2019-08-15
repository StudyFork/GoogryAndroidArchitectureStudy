package com.example.architecturestudy.ui

import com.example.architecturestudy.data.Coin

interface MarketContract {

    interface View {
        fun showTickerData(data: List<Coin>)

        fun clearTickerData()

        fun showProgressBar()

        fun hideProgressBar()
    }

    interface Presenter {

        fun loadData(keyMarket: String?)

        fun onFailCallback(errorMsg: String)

    }

}