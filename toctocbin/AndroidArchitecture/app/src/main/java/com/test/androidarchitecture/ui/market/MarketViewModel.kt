package com.test.androidarchitecture.ui.market

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.androidarchitecture.base.BaseViewModel
import com.test.androidarchitecture.data.MarketTitle
import com.test.androidarchitecture.data.source.UpbitRepository
import com.test.androidarchitecture.data.source.UpbitRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class MarketViewModel @Inject constructor(
    private val upbitRepositoryImpl : UpbitRepository
) : BaseViewModel() {

    private val _marketTitle = MutableLiveData<List<MarketTitle>>()
    var marketTitle: LiveData<List<MarketTitle>> = _marketTitle

    init {
        getMarketAll()
    }

    private fun getMarketAll() {
        upbitRepositoryImpl.getMarketAll()
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
                    showToastMessage(it.message.toString())
                }
            )
            .addTo(compositeDisposable)
    }
}