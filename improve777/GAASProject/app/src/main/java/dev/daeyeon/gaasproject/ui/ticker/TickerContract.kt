package dev.daeyeon.gaasproject.ui.ticker

import dev.daeyeon.gaasproject.data.source.UpbitRepository
import dev.daeyeon.gaasproject.ui.ticker.adapter.TickerAdapterContract

interface TickerContract {
    interface View {
        var presenter: Presenter

        fun showProgress()

        fun hideProgress()

        /**
         * ticker 에러 메시지 처리
         * @param msg
         */
        fun toastTickerFailMsg(msg: String)
    }

    interface Presenter {
        val view: View

        val upbitRepository: UpbitRepository

        val adapterView: TickerAdapterContract.View
        val adapterModel: TickerAdapterContract.Model

        fun start()

        fun loadUpbitTicker()
    }
}