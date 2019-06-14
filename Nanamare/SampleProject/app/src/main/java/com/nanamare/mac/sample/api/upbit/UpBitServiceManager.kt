package com.nanamare.mac.sample.api.upbit

import com.nanamare.mac.sample.api.ServiceDefine
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class UpBitServiceManager {

    companion object {

        private val SERVICE = ServiceDefine.retrofit.create(UpBitService::class.java)

        fun getAllMarketList(): Single<Response<List<MarketModel>>> {
            return SERVICE.getAllMarketList()
                .subscribeOn(Schedulers.io())
        }

        fun getTickerList(ticketList: MutableList<String>): Single<Response<List<TickerModel>>> {
            return SERVICE.getTickerList(ticketList.joinToString())
                .subscribeOn(Schedulers.io())
        }

    }

}
