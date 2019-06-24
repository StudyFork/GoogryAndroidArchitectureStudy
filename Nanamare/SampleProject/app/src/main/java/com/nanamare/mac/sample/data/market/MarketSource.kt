package com.nanamare.mac.sample.data.market

import com.nanamare.mac.sample.api.upbit.MarketModel
import com.nanamare.mac.sample.data.CommonSource

interface MarketSource : CommonSource {
    fun getMarketList(success: (List<MarketModel>) -> Unit, failed: () -> Unit)
}