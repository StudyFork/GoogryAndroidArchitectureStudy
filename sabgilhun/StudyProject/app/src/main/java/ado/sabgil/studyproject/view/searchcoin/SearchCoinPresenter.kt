package ado.sabgil.studyproject.view.searchcoin

import ado.sabgil.studyproject.data.CoinRepository
import ado.sabgil.studyproject.view.base.BasePresenter

class SearchCoinPresenter(
    private val searchCoinView: SearchCoinContract.View,
    private val coinRepository: CoinRepository
) : BasePresenter<SearchCoinContract.View>(searchCoinView), SearchCoinContract.Presenter {

    override fun searchCoin(coinName: String) {
    }

    override fun subscribeCoinData() {
    }

    override fun unSubscribeCoinData() {
    }
}