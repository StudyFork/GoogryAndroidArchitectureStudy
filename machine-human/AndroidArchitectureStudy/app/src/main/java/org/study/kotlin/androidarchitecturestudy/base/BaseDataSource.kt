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

        f = onTickerListLoaded(tickerList: ArrayList<TickerModel>)
        f = onDataNotAvailable(error: String)

    f = requestMarkets(marketName: String, callback: GetTickerListCallback)

 */
//콜백인터페이스 정의
interface BaseDataSource {

    interface GetTickerListCallback {
        fun onTickerListLoaded(tickerList: ArrayList<TickerModel>) {
            Log.e("TAG BaseDAtaSource", "onTickerListLoaded")
        }

        fun onDataNotAvailable(error: String) {
            Log.e("TAG BaseDAtaSource", "onDataNotAvailable")
        }
    }

    fun requestMarkets(marketName: String, callback: GetTickerListCallback) {
        Log.e("TAG BaseDAtaSource", "requestMarkets")
    }
}