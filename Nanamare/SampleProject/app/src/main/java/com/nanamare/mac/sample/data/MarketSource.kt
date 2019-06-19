package com.nanamare.mac.sample.data

import com.nanamare.mac.sample.api.upbit.MarketModel

interface MarketSource : CommonSource {
    fun getMarketList(success: (List<MarketModel>) -> Unit, failed: () -> Unit)
}