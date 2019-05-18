package dev.daeyeon.gaasproject.ui.ticker

import dev.daeyeon.gaasproject.data.source.UpbitDataSource

class TickerPresenter(
    val view: TickerContract.View,
    val upbitRepository: UpbitDataSource
) : TickerContract.Presenter {

    override var currencyArray: Array<String>? = null

    override var baseCurrency: String? = null


    override fun start() {
        loadUpbitTicker()
    }

    override fun loadUpbitTicker(searchTicker: String?) {
        view.showProgress()
        upbitRepository.getTicker(
            baseCurrency ?: "",
            searchTicker ?: "",
            success = {
                view.hideProgress()
                if (currencyArray.isNullOrEmpty()) {
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

    private fun initCurrentArray() {
        val list = arrayListOf<String>()
        list.add("전체")
        list.addAll(upbitRepository.markets.split(",")
            .map { market -> market.substringBefore("-") }
            .distinct())

        currencyArray = list.toTypedArray()
    }

    override fun cancelApi() {
        upbitRepository.cancelMarketCall()
        upbitRepository.cancelTickerCall()
    }
}