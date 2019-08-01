package org.study.kotlin.androidarchitecturestudy.data.source.remote

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.study.kotlin.androidarchitecturestudy.api.UpbitApi
import org.study.kotlin.androidarchitecturestudy.api.model.TickerModel
import org.study.kotlin.androidarchitecturestudy.api.retorifit.RetrofitBuilder
import org.study.kotlin.androidarchitecturestudy.base.BaseDataSource

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
class TickerRemoteDataSource( private val upbitApi: UpbitApi): BaseDataSource{

    override fun getTickerList(
        marketName: String,
        success: (List<TickerModel>) -> Unit,
        failed: (Throwable) -> Unit
    ) {
        upbitApi.getMarket()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { success ->
                    success.map { it.market }?.filter { it.substringBeforeLast("-") == marketName }
                        .joinToString().let {
                            upbitApi.getTicker(it)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(
                                    { result ->
                                        success(result)
                                    },
                                    { result ->
                                        failed(result)
                                    })
                        }
                },
                { failed(it) })
    }
}