package com.nanamare.mac.sample.data.market

import com.nanamare.mac.sample.api.upbit.MarketModel
import com.nanamare.mac.sample.base.BaseSource

interface MarketSource : BaseSource {
    fun getMarketList(success: (List<MarketModel>) -> Unit, failed: () -> Unit)
}