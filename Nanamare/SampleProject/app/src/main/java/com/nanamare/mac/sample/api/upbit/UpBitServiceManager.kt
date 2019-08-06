package com.nanamare.mac.sample.api.upbit

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class UpBitServiceManager(private val upBitService: UpBitService) {

    fun getAllMarketList(): Single<Response<List<MarketModel>>> {
        return upBitService.getAllMarketList()
            .subscribeOn(Schedulers.io())
    }

    fun getTickerList(ticketList: MutableList<String>): Single<Response<List<CoinModel>>> {
        return upBitService.getTickerList(ticketList.joinToString())
            .subscribeOn(Schedulers.io())
    }

}
