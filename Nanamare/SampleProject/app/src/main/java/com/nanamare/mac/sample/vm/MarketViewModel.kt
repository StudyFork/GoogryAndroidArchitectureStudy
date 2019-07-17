package com.nanamare.mac.sample.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nanamare.mac.sample.base.BaseViewModel
import com.nanamare.mac.sample.data.market.MarketRepository

class MarketViewModel: BaseViewModel() {

    private var _market = MutableLiveData<LinkedHashMap<String, List<String>>>()

    val market: LiveData<LinkedHashMap<String, List<String>>> get() = _market

    fun onMarketClick() {
        isLoadingObservable.value = true
        MarketRepository.getMarketList(success = {
            val marketMap: LinkedHashMap<String, List<String>> = LinkedHashMap()
            val marketList = listOf<String>().toMutableList()
            it.map { marketModel ->
                marketModel.market!!.split("-")[0].let { market ->
                    marketList.add(marketModel.market)
                    marketMap.put(market, marketList)
                }
            }
            isLoadingObservable.value = false
            _market.value = marketMap
        }, failed = {
            isLoadingObservable.value = false
            _market.value = LinkedHashMap()
        })
    }

    override fun close() {
        MarketRepository.close()
    }

}