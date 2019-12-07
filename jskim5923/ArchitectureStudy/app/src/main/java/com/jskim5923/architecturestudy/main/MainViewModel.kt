package com.jskim5923.architecturestudy.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jskim5923.architecturestudy.base.BaseViewModel
import com.jskim5923.architecturestudy.extension.getCoinCurrency
import com.jskim5923.architecturestudy.model.data.source.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: Repository
) : BaseViewModel() {
    private val _marketList = MutableLiveData<List<String>>()

    val marketList: LiveData<List<String>>
        get() = _marketList

    init {
        loadMarketList()
    }

    private fun loadMarketList() {
        repository.getMarketList()
            .subscribeOn(Schedulers.io())
            .map { marketList ->
                marketList.map {
                    it.market.getCoinCurrency()
                }.distinct()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _marketList.value = it
            }, {}
            )
            .addTo(compositeDisposable)
    }
}