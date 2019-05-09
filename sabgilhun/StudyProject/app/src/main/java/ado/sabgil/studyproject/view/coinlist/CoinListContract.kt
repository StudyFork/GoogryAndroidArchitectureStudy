package ado.sabgil.studyproject.view.coinlist

import ado.sabgil.studyproject.data.model.Ticker
import ado.sabgil.studyproject.view.base.BasePresenter
import ado.sabgil.studyproject.view.base.BaseView

interface CoinListContract {

    interface View : BaseView<Presenter> {
        fun updateCoinList(list: List<Ticker>)

        fun showToast(msg: String)

    }

    interface Presenter : BasePresenter

}