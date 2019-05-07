package ado.sabgil.studyproject.view.coinlist

import ado.sabgil.studyproject.data.model.Ticker

interface CoinListContract {

    interface View {
        var presenter: Presenter

        fun showProgressBar(flag: Boolean)

        fun updateList(list: List<Ticker>)

        fun showToast(msg: String)

    }

    interface Presenter {
        fun start()

        fun stop()

        fun refreshTickers()

    }
}