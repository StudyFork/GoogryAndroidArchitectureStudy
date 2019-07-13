package com.nanamare.mac.sample.vm

import com.nanamare.mac.sample.base.BaseViewModel
import com.nanamare.mac.sample.data.market.MarketRepository
import io.reactivex.subjects.PublishSubject

class MarketViewModel: BaseViewModel() {

    var marketObservable: PublishSubject<LinkedHashMap<String, List<String>>> = PublishSubject.create()

    fun onMarketClick() {
        isLoadingObservable.onNext(true)
        MarketRepository.getMarketList(success = {
            val marketMap: LinkedHashMap<String, List<String>> = LinkedHashMap()
            val marketList = listOf<String>().toMutableList()
            it.map { marketModel ->
                marketModel.market!!.split("-")[0].let { market ->
                    marketList.add(marketModel.market)
                    marketMap.put(market, marketList)
                }
            }
            isLoadingObservable.onNext(false)
            marketObservable.onNext(marketMap)
        }, failed = {
            isLoadingObservable.onNext(false)
            marketObservable.onError(Throwable())
        })
    }

    override fun close() {
        MarketRepository.close()
    }

}