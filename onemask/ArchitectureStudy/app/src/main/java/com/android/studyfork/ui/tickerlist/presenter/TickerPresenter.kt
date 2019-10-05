package com.android.studyfork.ui.tickerlist.presenter

import com.android.studyfork.base.BasePresenter
import com.android.studyfork.network.model.TickerResponse
import com.android.studyfork.repository.UpbitRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

/**
 * created by onemask
 */
class TickerPresenter(
    private val view: TickerContract.View
) : TickerContract.Presenter, BasePresenter() {

    override fun getTicker(market: String) {
        UpbitRepositoryImpl.getTickers(market)
            .map { it.map(TickerResponse::toTicker) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(view::setData, Timber::e)
            .addTo(compositeDisposable)
    }

}

