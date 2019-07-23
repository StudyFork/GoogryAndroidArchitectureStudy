package org.study.kotlin.androidarchitecturestudy.view.activity.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.study.kotlin.androidarchitecturestudy.api.model.ConvertTickerModel
import org.study.kotlin.androidarchitecturestudy.base.BaseViewModel
import org.study.kotlin.androidarchitecturestudy.data.TickerRepository
import org.study.kotlin.androidarchitecturestudy.util.FormatUtil

class MainViewModel(
    private val tickerRepository: TickerRepository
) : BaseViewModel() {

    protected val _observableTickerList = MutableLiveData<List<ConvertTickerModel>>()
    val observableTickerList: LiveData<List<ConvertTickerModel>> get() = _observableTickerList

    protected val _observableErrorMessage = MutableLiveData<Throwable>()
    val observableErrorMessage: LiveData<Throwable> get() = _observableErrorMessage

    fun getTickerListFromRemoteDataSource(marketName: String) {
        tickerRepository.getTickerList(marketName, success = { tickerList ->
            _observableTickerList.value = tickerList.map { FormatUtil.convertTo(it) }
        }, failed = { errorMessage ->
            Log.e("TAG", errorMessage.toString())
        })
    }

    override fun onDataNotAvailable(errorMessage: Throwable) {
        _observableErrorMessage.value = errorMessage
    }
}