package com.test.androidarchitecture.ui.market

import androidx.databinding.ObservableField
import com.test.androidarchitecture.base.BaseViewModel
import com.test.androidarchitecture.data.MarketTitle
import com.test.androidarchitecture.data.source.UpbitRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo

class MarketViewModel : BaseViewModel(){

    private val upbitRepository = UpbitRepository
    var marketTitle = ObservableField<List<MarketTitle>>()

    init {
        getMarketAll()
    }


    fun getMarketAll() {
        upbitRepository.getMarketAll()
            .map { list ->
                list.groupBy { it.market.substringBefore("-") }
                    .asSequence()
                    .map { (key, value) ->
                        MarketTitle(
                            marketTitle = key,
                            marketSearch = value.joinToString { it.market }
                        )
                    }
                    .toList()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    marketTitle.set(it)
                }, {
                    toastMessage.set(it.message.toString())
                }
            )
            .addTo(compositeDisposable)
    }
}