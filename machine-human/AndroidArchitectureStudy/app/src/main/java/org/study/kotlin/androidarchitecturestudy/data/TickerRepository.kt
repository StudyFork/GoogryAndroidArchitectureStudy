package org.study.kotlin.androidarchitecturestudy.data

import android.util.Log
import org.study.kotlin.androidarchitecturestudy.api.model.TickerModel
import org.study.kotlin.androidarchitecturestudy.base.BaseDataSource
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
class TickerRepository(
    val tickerRemoteDataSource: BaseDataSource
) : BaseDataSource {


    /**
        requestMarkets() = BaseDataSource의 requestMarkets()
     */
    override fun requestMarkets(marketName: String, callback: BaseDataSource.GetTickerListCallback) {
        Log.e("TAG TickerRepository", "requestMarkets")

        /**
            requestMarkets() = BaseDataSource의 requestMarkets()
            - 위 override 함수와 동일
         */
        tickerRemoteDataSource.requestMarkets(marketName, object : BaseDataSource.GetTickerListCallback {
            override fun onTickerListLoaded(tickerList: ArrayList<TickerModel>) {
                Log.e("TAG TickerRepository", "onTickerListLoaded")
                callback.onTickerListLoaded(tickerList)
            }

            /**
                requestMarkets() = BaseDataSource의 onDataNotAvailable()
             */
            override fun onDataNotAvailable(error: String) {
                Log.e("TAG TickerRepository", "onDataNotAvailable")
                callback.onDataNotAvailable(error)
            }

        })
    }
}