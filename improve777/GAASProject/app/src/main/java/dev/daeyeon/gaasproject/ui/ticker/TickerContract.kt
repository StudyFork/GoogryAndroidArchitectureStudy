package dev.daeyeon.gaasproject.ui.ticker

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

        fun start()

        fun loadUpbitTicker()
    }
}