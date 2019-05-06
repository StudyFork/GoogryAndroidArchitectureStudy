package ado.sabgil.studyproject.view.coinlist

import ado.sabgil.studyproject.data.model.Ticker
import ado.sabgil.studyproject.data.remote.upbit.UpbitApiHandlerImpl
import ado.sabgil.studyproject.enums.BaseCurrency

class CoinListPresenter(
    private val baseCurrency: BaseCurrency,
    private val coinListView: CoinListContract.View
) : CoinListContract.Presenter {

    private val dataSource = UpbitApiHandlerImpl

    init {
        coinListView.presenter = this
    }

    override fun start() {
        loadTickers()
    }

    override fun loadTickers() {
        coinListView.showProgressBar(true)

        dataSource.getAllTickers(
            baseCurrency,
            { result ->
                coinListView.showProgressBar(false)
                coinListView.updateList(result.map { Ticker.from(it) }.toMutableList())
            },
            {})
    }
}