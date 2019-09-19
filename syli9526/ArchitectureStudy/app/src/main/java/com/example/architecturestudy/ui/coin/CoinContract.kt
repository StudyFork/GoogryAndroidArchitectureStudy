package com.example.architecturestudy.ui.coin

import com.example.architecturestudy.data.model.Ticker

interface CoinContract {

    interface View {

        fun setData(tickerList: List<Ticker>)

        fun showMessage(message: String)

    }

    interface Presenter {

        fun getTickerList(marketName: String)

    }
}