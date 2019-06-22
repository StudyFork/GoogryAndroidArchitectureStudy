package com.studyfirstproject.showcoin

import com.studyfirstproject.data.CoinDataSource
import com.studyfirstproject.data.CoinRepository
import com.studyfirstproject.data.model.TickerModel

class CoinPresenter(private val view: CoinContract.View) :
    CoinContract.Presenter, CoinDataSource.LoadMarketsCallback, CoinDataSource.LoadTickersCallback {
    private var repository: CoinDataSource = CoinRepository()

    override fun start() {
    }

    override fun getMarketData() {
        view.showProgress()
        repository.getAllMarkets(this)
    }

    override fun onMarketsLoaded(markets: String) {
        view.hideProgress()
        view.hideRefreshIcon()
        repository.getCoinData(markets, this)
    }

    override fun onCoinsLoaded(tickers: List<TickerModel>) {
        view.setCoinInfo(tickers)
    }

    override fun onDataNotAvailable(msg: String, reason: String?) {
        view.notifyError(msg, reason)
    }
}