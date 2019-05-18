package dev.daeyeon.gaasproject.ui.ticker

import dev.daeyeon.gaasproject.data.source.UpbitRepository

class TickerPresenter(
    val view: TickerContract.View,
    val upbitRepository: UpbitRepository
) : TickerContract.Presenter {

    override var currencyArray: Array<String>? = null

    override var baseCurrency: String? = null


    override fun start() {
        loadUpbitTicker()
    }

    override fun loadUpbitTicker() {
        view.showProgress()
        upbitRepository.getTicker(
            baseCurrency ?: "",
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