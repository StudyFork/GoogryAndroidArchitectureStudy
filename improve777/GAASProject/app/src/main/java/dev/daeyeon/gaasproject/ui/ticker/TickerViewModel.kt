package dev.daeyeon.gaasproject.ui.ticker

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.daeyeon.gaasproject.data.Ticker
import dev.daeyeon.gaasproject.data.source.UpbitDataSource
import dev.daeyeon.gaasproject.util.Event

class TickerViewModel(
    private val upbitRepository: UpbitDataSource
) : ViewModel() {

    private val _tickerList = MutableLiveData<List<Ticker>>(emptyList())
    val tickerList: LiveData<List<Ticker>>
        get() = _tickerList

    /**
     * 통신 실패시 메시지
     */
    private val _failMsgEvent = MutableLiveData<Event<String>>()
    val failMsgEvent: LiveData<Event<String>>
        get() = _failMsgEvent

    /**
     * 프로그레스바
     */
    private val _isShowProgressBar = MutableLiveData<Boolean>(false)
    val isShowProgressBar: LiveData<Boolean>
        get() = _isShowProgressBar

    /**
     * 검색어 two-way binding
     */
    val searchText = MutableLiveData<String>("")

    /**
     * 기준 마켓
     */
    private val _baseMarket = MutableLiveData<String>("")
    val baseMarket: LiveData<String> get() = _baseMarket

    init {
        loadUpbitTicker()
    }

    fun loadUpbitTicker() {
        _isShowProgressBar.value = true

        upbitRepository.getTicker(
            baseCurrency = _baseMarket.value!!,
            searchTicker = searchText.value ?: UpbitDataSource.ALL_CURRENCY,
            success = {
                _isShowProgressBar.value = false
                _tickerList.value = it
            },
            fail = {
                _isShowProgressBar.value = false
                _failMsgEvent.value = Event(it)
            }
        )
    }

    fun setBaseMarket(newBaseMarket: String) {
        _baseMarket.value =
            if (newBaseMarket == UpbitDataSource.ALL_CURRENCY) {
                ""
            } else {
                newBaseMarket
            }
    }

    fun getMarkets(): String = upbitRepository.markets

    fun cancelApi() {
        upbitRepository.unsubscribeTicker()
    }

    override fun onCleared() {
        Log.e("TickerViewModel", "onCleared()")
        cancelApi()
        super.onCleared()
    }
}