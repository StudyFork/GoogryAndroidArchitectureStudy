package com.architecture.study.view.coin.presenter

import com.architecture.study.network.model.MarketResponse
import com.architecture.study.view.base.BasePresenter
import com.architecture.study.view.base.BaseView

interface CoinListActivityContract {
    interface View : BaseView<Presenter> {
        fun setTabPager(marketList: List<MarketResponse>) // Fragment로 마켓리스트 보냄

        fun showMessage(message: String)
    }

    interface Presenter : BasePresenter{
        var isConnectApi: Boolean

        fun getMarketList() // 마켓 목록 가져옴
    }
}