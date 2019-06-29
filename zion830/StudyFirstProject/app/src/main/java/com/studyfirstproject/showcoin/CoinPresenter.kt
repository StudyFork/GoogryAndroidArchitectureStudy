package com.studyfirstproject.showcoin

import com.studyfirstproject.data.CoinDataSource
import com.studyfirstproject.data.CoinRepository
import com.studyfirstproject.data.model.TickerModel

class CoinPresenter(
    private val view: CoinContract.View,
    private val repository: CoinDataSource
) : CoinContract.Presenter,
    CoinDataSource.LoadMarketsCallback,
    CoinDataSource.LoadTickersCallback {

    override fun start() {
    }

    override fun getMarketData() {
        view.showProgress()
        repository.getAllMarkets(this)
    }

    override fun onMarketsLoaded(markets: String) {
        repository.getCoinData(markets, this)
    }

    override fun onCoinsLoaded(tickers: List<TickerModel>) {
        view.hideProgress()
        view.hideRefreshIcon()
        view.setCoinInfo(tickers)
    }

    override fun onDataNotAvailable(msg: String, reason: String?) {
        view.notifyError(msg, reason)
    }
}