package com.nanamare.mac.sample.data.market

import com.nanamare.mac.sample.api.upbit.MarketModel

class MarketRepository(private val marketRemoteDataSource: MarketRemoteDataSource) : MarketSource {

    override fun getMarketList(success: (List<MarketModel>) -> Unit, failed: () -> Unit) {
        marketRemoteDataSource.getMarketList(success, failed)
    }

    override fun close() {
        marketRemoteDataSource.close()
    }


}