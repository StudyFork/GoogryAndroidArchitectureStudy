package ado.sabgil.studyproject.view.coinlist

import ado.sabgil.studyproject.data.model.Ticker
import ado.sabgil.studyproject.view.base.BaseContract

interface CoinListContract {

    interface View : BaseContract.View {

        fun updateCoinList(list: List<Ticker>)

    }

    interface Presenter : BaseContract.Presenter {

        fun subscribeRemote()

        fun unSubscribeRemote()

    }
}