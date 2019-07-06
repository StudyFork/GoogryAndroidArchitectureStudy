package org.study.kotlin.androidarchitecturestudy.view.activity.main

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import org.study.kotlin.androidarchitecturestudy.api.model.TickerModel
import org.study.kotlin.androidarchitecturestudy.base.BaseDataSource

class MainViewModel(
    remoteDataSource: BaseDataSource,
    message: String
) : BaseDataSource.GetTickerListCallback {
    var observableTickerList = ObservableField<List<TickerModel>>()
    var observableErrorMessage = MutableLiveData<Throwable>()

    init {
        remoteDataSource.requestMarkets(message,this)
    }
    override fun onTickerListLoaded(tickerList: List<TickerModel>) {
        observableTickerList.set(tickerList)
    }

    override fun onDataNotAvailable(errorMessage: Throwable) {
        observableErrorMessage?.value = errorMessage
    }
}