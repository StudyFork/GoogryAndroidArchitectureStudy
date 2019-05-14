package dev.daeyeon.gaasproject.ui.ticker

import dev.daeyeon.gaasproject.data.source.UpbitRepository

class TickerPresenter(
    val view: TickerContract.View,
    val upbitRepository: UpbitRepository
) : TickerContract.Presenter {

    override fun start() {
        loadUpbitTicker()
    }

    override fun loadUpbitTicker() {
        view.showProgress()
        upbitRepository.getTicker(
            success = {
                view.hideProgress()
                view.replaceTickerList(it)
            },
            fail = {
                view.hideProgress()
                view.toastTickerFailMsg(it)
            }
        )
    }

}