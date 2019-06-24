package com.nanamare.mac.sample.ui.market

import android.os.Bundle
import com.google.gson.Gson
import com.nanamare.mac.sample.data.market.MarketRepository
import com.nanamare.mac.sample.ui.MainActivity

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
            val bundle = Bundle().apply {
                putString(MainActivity.KET_MARKET_LIST, Gson().toJson(marketMap))
            }
            view.goToFragment(MarketListFragment::class.java, bundle)
        }, failed = {
            view.hideLoadingDialog()
        })

    }

    override fun close() {
        MarketRepository.close()
    }
}