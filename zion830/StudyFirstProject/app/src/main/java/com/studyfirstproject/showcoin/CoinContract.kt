package com.studyfirstproject.showcoin

import com.studyfirstproject.BasePresenter
import com.studyfirstproject.BaseView
import com.studyfirstproject.data.model.TickerModel

interface CoinContract {

    interface View : BaseView<Presenter> {

        fun initView()

        fun hideRefreshIcon()

        fun setCoinInfo(coinData: List<TickerModel>)

        fun notifyError(msg: String, reason: String?)
    }

    interface Presenter : BasePresenter {

        fun getMarketData()
    }
}