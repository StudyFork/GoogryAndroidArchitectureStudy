package com.nanamare.mac.sample.data.market

import com.nanamare.mac.sample.api.upbit.MarketModel

object MarketRepository : MarketSource {

    override fun getMarketList(success: (List<MarketModel>) -> Unit, failed: () -> Unit) {
        MarketRemoteDataSource.getMarketList(success, failed)
    }

    override fun close() {
        MarketRemoteDataSource.close()
    }


}