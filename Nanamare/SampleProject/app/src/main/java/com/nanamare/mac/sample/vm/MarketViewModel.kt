package com.nanamare.mac.sample.vm

import com.nanamare.mac.sample.data.market.MarketRepository
import com.nanamare.mac.sample.ui.market.MarketNavigator

class MarketViewModel {

    var navigator: MarketNavigator? = null

    fun onMarketClick() {
        navigator?.showLoadingDialog()
        MarketRepository.getMarketList(success = {
            navigator?.hideLoadingDialog()
            val marketMap: LinkedHashMap<String, List<String>> = LinkedHashMap()
            val marketList = listOf<String>().toMutableList()
            it.map { marketModel ->
                marketModel.market!!.split("-")[0].let { market ->
                    marketList.add(marketModel.market)
                    marketMap.put(market, marketList)
                }
            }
            navigator?.sendMarketList(marketMap)
        }, failed = {
            navigator?.hideLoadingDialog()
        })
    }

    fun onClose() {
        MarketRepository.close()
    }

}