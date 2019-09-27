package com.example.seonoh.seonohapp.contract

import com.example.seonoh.seonohapp.model.Market

interface CoinMainContract : BaseContract {
    interface View : BaseContract.View {
        fun showToast()
        fun setPager(marketData: List<Market>)
    }

    interface Presenter : BaseContract.Presenter {
        fun loadMarketData()
        fun classifyMarketData(marketData: List<Market>): ArrayList<String>
    }
}