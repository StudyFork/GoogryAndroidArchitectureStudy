package dev.daeyeon.gaasproject.ui.ticker

import dev.daeyeon.gaasproject.data.source.UpbitRepository
import dev.daeyeon.gaasproject.ui.ticker.adapter.TickerAdapterContract

class TickerPresenter(val view: TickerContract.View,
                      val upbitRepository: UpbitRepository,
                      val adapterView: TickerAdapterContract.View,
                      val adapterModel: TickerAdapterContract.Model
) : TickerContract.Presenter {

    override fun start() {
        loadUpbitTicker()
    }

    override fun loadUpbitTicker() {
        view.showProgress()
        upbitRepository.getTicker(
                success = {
                    view.hideProgress()
                    adapterModel.replaceList(it)
                    adapterView.notifyAdapter()
                },
                fail = {
                    view.hideProgress()
                    view.toastTickerFailMsg(it)
                }
        )
    }

}