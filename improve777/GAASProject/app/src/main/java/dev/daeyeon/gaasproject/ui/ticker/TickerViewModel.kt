package dev.daeyeon.gaasproject.ui.ticker

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import dev.daeyeon.gaasproject.data.Ticker
import dev.daeyeon.gaasproject.data.source.UpbitDataSource
import dev.daeyeon.gaasproject.util.Event

class TickerViewModel(private val upbitRepository: UpbitDataSource) {

    private val _tickerList = MutableLiveData<List<Ticker>>().apply { value = emptyList() }
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
    private val _isShowProgressBar = MutableLiveData<Boolean>()
    val isShowProgressBar: LiveData<Boolean>
        get() = _isShowProgressBar

    /**
     * 검색어 two-way binding
     */
    val searchText = MutableLiveData<String>()

    /**
     * 마켓 어레이
     */
    private lateinit var currencyArray: Array<String>

    /**
     * 기준 마켓
     */
    private lateinit var baseCurrency: String

    fun loadUpbitTicker() {
        _isShowProgressBar.value = true

        upbitRepository.getTicker(
            baseCurrency = getBaseCurrency(),
            searchTicker = searchText.value ?: UpbitDataSource.ALL_CURRENCY,
            success = {
                _isShowProgressBar.value = false
                _tickerList.value = it

                searchText.value = ""
            },
            fail = {
                _isShowProgressBar.value = false
                _failMsgEvent.value = Event(it)
            }
        )
    }

    fun onRefresh() {
        loadUpbitTicker()
    }

    fun getBaseCurrency() = if (!::baseCurrency.isInitialized) "" else baseCurrency

    fun setBaseCurrency(baseCurrency: String) {
        this.baseCurrency = if (baseCurrency == UpbitDataSource.ALL_CURRENCY) "" else baseCurrency
    }

    fun getCurrencyArray(): Array<String> {
        if (!::currencyArray.isInitialized || (currencyArray.size <= 1)) {
            setCurrencyArray()
        }
        return currencyArray
    }

    private fun setCurrencyArray() {
        val list = arrayListOf(UpbitDataSource.ALL_CURRENCY)

        if (upbitRepository.markets.isNotEmpty()) {
            list.addAll(upbitRepository.markets.split(",")
                .map { market -> market.substringBefore("-") }
                .distinct()
            )
        }

        currencyArray = list.toTypedArray()
    }

    fun cancelApi() {
        upbitRepository.unsubscribeTicker()
    }
}