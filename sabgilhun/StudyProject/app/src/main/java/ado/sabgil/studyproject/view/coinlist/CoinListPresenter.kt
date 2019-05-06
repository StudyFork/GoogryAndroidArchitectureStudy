package ado.sabgil.studyproject.view.coinlist

import ado.sabgil.studyproject.enums.BaseCurrency

class CoinListPresenter(
    private val baseCurrency: BaseCurrency,
    private val coinListView: CoinListContract.View
) : CoinListContract.Presenter {

    init {
        coinListView.presenter = this
    }

    override fun start() {
    }

    override fun loadTickers() {
    }
}