package com.android.studyfork.ui.tickerlist.viewmodel

import androidx.databinding.ObservableField
import com.android.studyfork.base.BaseViewModel
import com.android.studyfork.network.model.Ticker
import com.android.studyfork.network.model.TickerResponse
import com.android.studyfork.repository.UpbitRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers

class TickerViewModel : BaseViewModel() {

    val tickerList = ObservableField<List<Ticker>>()

    fun getTicker(market: String) {
        UpbitRepositoryImpl.run {
            getTickers(market)
                .map {
                    tickerList.set(it.map(TickerResponse::toTicker))
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, { it.printStackTrace() })
        }
    }


}