package ado.sabgil.studyproject.view.searchcoin

import ado.sabgil.studyproject.data.model.Ticker
import ado.sabgil.studyproject.view.base.BaseContract

interface SearchCoinContract {

    interface View : BaseContract.View {
        fun updateCoinList(list: List<Ticker>)
    }

    interface Presenter : BaseContract.Presenter {
        fun searchCoin(coinName: String)
        fun subscribeCoinData()
        fun unSubscribeCoinData()
    }

}