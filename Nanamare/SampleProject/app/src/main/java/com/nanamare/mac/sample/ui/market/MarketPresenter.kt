package com.nanamare.mac.sample.ui.market

import com.nanamare.mac.sample.data.market.MarketRepository

class MarketPresenter(private val view: MarketContract.MarketView) : MarketContract.MarketPresenter {

    override fun getMarketList() {
        view.showLoadingDialog()
        MarketRepository.getMarketList(success = {
            view.hideLoadingDialog()
            val marketMap: LinkedHashMap<String, List<String>> = LinkedHashMap()
            val marketList = listOf<String>().toMutableList()
            it.map { marketModel ->
                marketModel.market!!.split("-")[0].let { market ->
                    marketList.add(marketModel.market)
                    marketMap.put(market, marketList)
                }
            }
            view.showMarketList(marketMap)
        }, failed = {
            view.hideLoadingDialog()
        })

    }

    override fun close() {
        MarketRepository.close()
    }
}