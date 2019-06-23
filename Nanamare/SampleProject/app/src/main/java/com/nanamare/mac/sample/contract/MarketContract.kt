package com.nanamare.mac.sample.contract

import com.nanamare.mac.sample.base.BasePresenter
import com.nanamare.mac.sample.base.BaseView

interface MarketContract {

    interface MarketView : BaseView

    interface MarketPresenter : BasePresenter {
        fun getMarketList()
    }

}