package dev.daeyeon.gaasproject.ui.ticker

import dev.daeyeon.gaasproject.data.source.UpbitDataSource

class TickerPresenter(
    val view: TickerContract.View,
    val upbitRepository: UpbitDataSource
) : TickerContract.Presenter {

    private lateinit var currencyArray: Array<String>

    private lateinit var baseCurrency: String

    override fun start() {
        loadUpbitTicker()
    }

    override fun loadUpbitTicker(searchTicker: String?) {
        view.showProgress()
        upbitRepository.getTicker(
            getBaseCurrency(),
            searchTicker ?: "",
            success = {
                view.hideProgress()
                if (getCurrencyArray().isEmpty()) {
                    initCurrentArray()
                }
                view.replaceTickerList(it)
            },
            fail = {
                view.hideProgress()
                view.toastTickerFailMsg(it)
            }
        )
    }

    override fun setBaseCurrency(baseCurrency: String) {
        this.baseCurrency = if (baseCurrency == ALL_CURRENCY) "" else baseCurrency
    }

    override fun getBaseCurrency() = if (!::baseCurrency.isInitialized) "" else baseCurrency

    override fun getCurrencyArray() = if (!::currencyArray.isInitialized) emptyArray() else currencyArray

    private fun initCurrentArray() {
        val list = arrayListOf<String>()
        list.add(ALL_CURRENCY)
        list.addAll(upbitRepository.markets.split(",")
            .map { market -> market.substringBefore("-") }
            .distinct())

        currencyArray = list.toTypedArray()
    }

    override fun cancelApi() {
        upbitRepository.cancelMarketCall()
        upbitRepository.cancelTickerCall()
    }

    companion object {
        private const val ALL_CURRENCY = "전체"
    }
}