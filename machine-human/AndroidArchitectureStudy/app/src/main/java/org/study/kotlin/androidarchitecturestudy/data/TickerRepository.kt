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

f = onTickerListLoaded(tickerList: List<TickerModel>=
f = onDataNotAvailable(error: String)

 */
class TickerRepository private constructor(
    val tickerRemoteDataSource: BaseDataSource
) : BaseDataSource {

    override fun requestMarkets(marketName: String, callback: BaseDataSource.GetTickerListCallback) {

        tickerRemoteDataSource.requestMarkets(marketName, object : BaseDataSource.GetTickerListCallback {
            override fun onTickerListLoaded(tickerList: List<TickerModel>) =
                callback.onTickerListLoaded(tickerList)
            override fun onDataNotAvailable(errorMessage: Throwable) {
                callback.onDataNotAvailable(errorMessage)
            }

        })
    }

    companion object {
        private var instance: TickerRepository? = null
        operator fun invoke(tickerRemoteDataSource: BaseDataSource): TickerRepository {
            Log.e("TAG", "repositoryinvoke")
            return instance ?: TickerRepository(tickerRemoteDataSource = tickerRemoteDataSource)
                .apply { instance = this }
        }
    }
}