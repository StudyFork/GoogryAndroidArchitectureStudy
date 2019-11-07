package com.test.androidarchitecture.ui.market

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.androidarchitecture.base.BaseViewModel
import com.test.androidarchitecture.data.MarketTitle
import com.test.androidarchitecture.data.source.UpbitRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo

class MarketViewModel : BaseViewModel() {

    private val upbitRepository = UpbitRepository
    private var _marketTitle = MutableLiveData<List<MarketTitle>>()
    var marketTitle: LiveData<List<MarketTitle>> = _marketTitle

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
                    _marketTitle.value = it
                }, {
                    _toastMessage.value = it.message.toString()
                }
            )
            .addTo(compositeDisposable)
    }
}