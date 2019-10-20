package com.android.studyfork.ui.tickerlist.viewmodel

import androidx.databinding.ObservableField
import com.android.studyfork.base.BaseViewModel
import com.android.studyfork.network.model.Ticker
import com.android.studyfork.repository.UpbitRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo

class TickerViewModel : BaseViewModel() {

    val tickerList = ObservableField<List<Ticker>>()

    fun getTicker(market: String) {
        UpbitRepositoryImpl.getTickers(market)
            .map { tickerList ->
                tickerList.map { ticker ->
                    ticker::toTicker.invoke()
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                tickerList.set(it)
            }, { it.printStackTrace() })
            .addTo(disposable)
    }
}

