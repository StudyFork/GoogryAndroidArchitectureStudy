package com.nanamare.mac.sample.ui.market

import com.nanamare.mac.sample.base.BasePresenter
import com.nanamare.mac.sample.base.BaseView

interface MarketContract {

    interface MarketView : BaseView

    interface MarketPresenter : BasePresenter {
        fun getMarketList()
    }

}