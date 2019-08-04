package com.example.architecturestudy.ui

import com.example.architecturestudy.data.Coin
import com.example.architecturestudy.data.CoinTickerResponse

interface MarketContract {

    interface View {
        fun setTickerData(data: List<Coin>)

        fun clearTickerData()
    }

    interface Presenter {

        fun loadData(keyMarket: String?)

        fun onFailCallback(errorMsg: String)

        fun convertTickerIntoCoin(ticker: CoinTickerResponse): Coin
    }

}