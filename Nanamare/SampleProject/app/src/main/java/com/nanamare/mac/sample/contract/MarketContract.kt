package com.nanamare.mac.sample.contract

import com.nanamare.mac.sample.presenter.BasePresenter
import com.nanamare.mac.sample.presenter.BaseView

interface MarketContract {

    interface MarketView : BaseView

    interface MarketPresenter : BasePresenter {
        fun getMarketList()
    }

}