package dev.daeyeon.gaasproject.ui.ticker

import dev.daeyeon.gaasproject.data.Ticker
import dev.daeyeon.gaasproject.data.source.UpbitDataSource
import kotlin.properties.Delegates

class TickerViewModel(private val upbitRepository: UpbitDataSource) {

    private var tickerList by Delegates.observable<List<Ticker>>(
        arrayListOf()
    ) { _, _, newValue ->
        tickerAdapter.replaceList(newValue)
    }

    /**
     * 통신 실패시 메시지
     */
    private var failMsg by Delegates.observable("") { _, _, newValue ->
        toastTickerFailMsg(newValue)
    }

    /**
     * 프로그레스바
     */
    private var isShowProgressBar by Delegates.observable(false) { _, oldValue, newValue ->
        if (oldValue == newValue) {
            return@observable
        }

        binding.srlTicker.isRefreshing = newValue
    }

    /**
     * 마켓 어레이
     */
    private lateinit var currencyArray: Array<String>

    /**
     * 기준 마켓
     */
    private lateinit var baseCurrency: String

    fun loadUpbitTicker(searchTicker: String? = null) {
        isShowProgressBar = true

        upbitRepository.getTicker(
            getBaseCurrency(),
            searchTicker ?: UpbitDataSource.ALL_CURRENCY,
            success = {
                isShowProgressBar = false
                tickerList = it
            },
            fail = {
                isShowProgressBar = false
                failMsg = it
            }
        )
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
        upbitRepository.cancelMarketCall()
        upbitRepository.cancelTickerCall()
    }
}