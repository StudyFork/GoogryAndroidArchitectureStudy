package ado.sabgil.studyproject.view.coinlist

import ado.sabgil.studyproject.data.model.Ticker
import ado.sabgil.studyproject.view.BasePresenter
import ado.sabgil.studyproject.view.BaseView

interface CoinListContract {
    interface View : BaseView<Presenter> {
        fun showProgressBar(flag: Boolean)
        fun updateList(list: List<Ticker>)
    }

    interface Presenter : BasePresenter {
        fun loadTickers()
    }
}