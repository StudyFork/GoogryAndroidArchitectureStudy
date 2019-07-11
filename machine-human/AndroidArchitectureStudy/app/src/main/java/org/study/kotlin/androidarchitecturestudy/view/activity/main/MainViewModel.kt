package org.study.kotlin.androidarchitecturestudy.view.activity.main

import androidx.lifecycle.MutableLiveData
import androidx.databinding.ObservableField
import org.study.kotlin.androidarchitecturestudy.api.model.TickerModel
import org.study.kotlin.androidarchitecturestudy.base.BaseDataSource

class MainViewModel(
    remoteDataSource: BaseDataSource,
    marketName: String
) : BaseViewModel {

    var observableTickerList = ObservableField<List<TickerModel>>()
    var observableErrorMessage = ObservableField<Throwable>()

    init {
        remoteDataSource.requestMarkets(marketNames,this)
    }
    override fun onTickerListLoaded(tickerList: List<TickerModel>) {
        observableTickerList.set(tickerList)
    }

    override fun onDataNotAvailable(errorMessage: Throwable) {
        observableErrorMessage?.value = errorMessage
    }
}