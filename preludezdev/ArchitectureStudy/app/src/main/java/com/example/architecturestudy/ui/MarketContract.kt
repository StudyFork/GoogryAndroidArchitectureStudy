package com.example.architecturestudy.ui

import com.example.architecturestudy.data.Coin
import com.example.architecturestudy.data.CoinTickerResponse

interface MarketContract {

    interface View {
        fun showTickerList(data: List<Coin>)
    }

    interface Presenter {

        fun loadData(keyMarket: String)

        fun onFailCallBack(errorMsg: String)

        fun convertTickerIntoCoin(ticker: CoinTickerResponse): Coin
    }

}