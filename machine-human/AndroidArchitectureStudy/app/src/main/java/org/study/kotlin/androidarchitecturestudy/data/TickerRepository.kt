package org.study.kotlin.androidarchitecturestudy.data

import org.study.kotlin.androidarchitecturestudy.api.model.TickerModel
import org.study.kotlin.androidarchitecturestudy.base.BaseDataSource
import org.study.kotlin.androidarchitecturestudy.base.BaseRepository

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
class TickerRepository (
    val tickerRemoteDataSource: BaseDataSource
) : BaseRepository {

    override fun getTickerList(
        marketName: String,
        success: (List<TickerModel>) -> Unit,
        failed: (Throwable) -> Unit
    ) {
        tickerRemoteDataSource.getTickerList(marketName, success, failed)
    }
}