package com.nanamare.mac.sample.data.market

import com.nanamare.mac.sample.api.upbit.MarketModel

class MarketRepository(private val marketSource: MarketSource) : MarketSource {

    override fun getMarketList(success: (List<MarketModel>) -> Unit, failed: () -> Unit) {
        marketSource.getMarketList(success, failed)
    }

    override fun close() {
        marketSource.close()
    }


}