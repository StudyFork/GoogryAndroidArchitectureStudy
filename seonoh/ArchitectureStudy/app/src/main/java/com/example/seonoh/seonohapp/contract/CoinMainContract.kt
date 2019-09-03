package com.example.seonoh.seonohapp.contract

import com.example.seonoh.seonohapp.model.Market

interface CoinMainContract {
    interface View {
        fun initView()
        fun showToast()
        fun setPager(marketData: List<Market>)
    }

    interface Presenter {
        fun clearDisposable()
        fun loadMarketData()
        fun classifyMarketData(marketData: List<Market>): ArrayList<String>
    }
}