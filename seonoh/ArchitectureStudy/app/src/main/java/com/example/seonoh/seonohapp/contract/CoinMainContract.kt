package com.example.seonoh.seonohapp.contract

import com.example.seonoh.seonohapp.CoinMainPresenter
import com.example.seonoh.seonohapp.model.Market

interface CoinMainContract {
    interface View {
        var presenter: CoinMainPresenter
        fun initView()
        fun showToast()
        fun setPager(marketData: List<Market>)
    }

    interface Presenter {
        fun loadMarketData()
        fun classifyMarketData(marketData: List<Market>): ArrayList<String>
    }
}