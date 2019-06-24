package com.nanamare.mac.sample.ui.coin

import com.nanamare.mac.sample.api.upbit.TickerModel
import com.nanamare.mac.sample.base.BasePresenter
import com.nanamare.mac.sample.base.BaseView

interface CoinContract {

    interface CoinView : BaseView {
        fun showCoins(list: List<TickerModel>)
    }

    interface CoinPresenter : BasePresenter {
        fun getCoins(ticketList: MutableList<String>)
    }

}