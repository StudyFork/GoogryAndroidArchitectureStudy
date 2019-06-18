package com.nanamare.mac.sample.contract

import com.nanamare.mac.sample.api.upbit.TickerModel
import com.nanamare.mac.sample.presenter.BasePresenter
import com.nanamare.mac.sample.presenter.BaseView

interface CoinContract {

    interface CoinView : BaseView {
        fun showCoins(list: List<TickerModel>?)
    }

    interface CoinPresenter : BasePresenter {
        fun getCoins(ticketList: MutableList<String>)
    }

}