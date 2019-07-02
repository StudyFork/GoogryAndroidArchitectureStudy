package com.nanamare.mac.sample.ui.market

import com.nanamare.mac.sample.base.BaseNavigator

interface MarketNavigator : BaseNavigator {
    fun sendMarketList(marketMap: LinkedHashMap<String, List<String>>)
}