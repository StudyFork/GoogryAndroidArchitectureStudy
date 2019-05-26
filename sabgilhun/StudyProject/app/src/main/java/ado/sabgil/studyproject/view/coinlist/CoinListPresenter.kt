package ado.sabgil.studyproject.view.coinlist

import ado.sabgil.studyproject.data.CoinRepository
import ado.sabgil.studyproject.view.base.BasePresenter

class CoinListPresenter(
    private val baseCurrency: String,
    private val coinListView: CoinListContract.View,
    private val coinRepository: CoinRepository
) : BasePresenter<CoinListContract.View>(coinListView), CoinListContract.Presenter {

    override fun subscribeRemote() {
        disposables.add(coinRepository.getCoinDataChangeWithCurrency(
            baseCurrency, {
                coinListView.updateCoinList(it)
            }, {
                coinListView.showToastMessage(it)
            }
        ))
    }

    override fun unSubscribeRemote() {
        coinRepository.unSubscribeCoinDataChange()
        disposables.clear()
    }
}