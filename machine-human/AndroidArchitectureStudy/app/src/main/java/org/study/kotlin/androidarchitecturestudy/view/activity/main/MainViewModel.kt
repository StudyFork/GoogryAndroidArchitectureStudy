package org.study.kotlin.androidarchitecturestudy.view.activity.main

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import org.study.kotlin.androidarchitecturestudy.api.model.TickerModel
import org.study.kotlin.androidarchitecturestudy.base.BaseDataSource
import org.study.kotlin.androidarchitecturestudy.base.BaseViewModel

class MainViewModel(
    remoteDataSource: BaseDataSource,
    marketName: String
) : BaseViewModel() {
    val _observableTickerList = MutableLiveData<List<ConvertTickerModel>>()
    val _observableErrorMessage = MutableLiveData<Throwable>()

    val observableTickerList: LiveData<List<ConvertTickerModel>>
        get() = _observableTickerList

    val observableErrorMessage: LiveData<Throwable>
        get() = _observableErrorMessage

    var observableTickerList = ObservableField<List<TickerModel>>()
    var observableErrorMessage = ObservableField<Throwable>()

    init {
        remoteDataSource.getTickerList(marketName, success = { tickerList ->
            observableTickerList.set(tickerList)
        }, failed = { errorMessage ->
        })
    }

    override fun onDataNotAvailable(errorMessage: Throwable) {
        observableErrorMessage.set(errorMessage)
    }
}