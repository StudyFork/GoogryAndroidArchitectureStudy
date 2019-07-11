package org.study.kotlin.androidarchitecturestudy.base

import android.util.Log
import org.study.kotlin.androidarchitecturestudy.api.model.TickerModel

/**
***************************
BaseDataSource - structure

i = interface
f = function
***************************

i = BaseDataSource

    i = GetTickerListCallback

        f = onTickerListLoaded(tickerList: List<TickerModel>)
        f = onDataNotAvailable(error: String)

    f = requestMarkets(marketName: String, callback: GetTickerListCallback)

 */
//콜백인터페이스 정의
interface BaseDataSource {
    fun getTickerList(marketName: String, success: (List<TickerModel>) -> Unit, failed: (Throwable) -> Unit)
}