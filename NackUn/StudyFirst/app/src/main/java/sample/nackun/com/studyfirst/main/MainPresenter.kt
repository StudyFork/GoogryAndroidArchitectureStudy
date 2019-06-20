package sample.nackun.com.studyfirst.main

import sample.nackun.com.studyfirst.data.DataSource
import sample.nackun.com.studyfirst.data.Repository
import sample.nackun.com.studyfirst.vo.Ticker

class MainPresenter(val repository: Repository, val mainView: MainContract.View) : MainContract.Presenter,
    DataSource.RequestTickersCallback {

    //lateinit var arr:ArrayList<Ticker>

    init {
        mainView.presenter = this
    }

    override fun start() {
    }

    override fun requestTickers(marketName: String) {
        repository.requestMarkets(marketName, this)
    }

    override fun onGetError(err: String?) {
        mainView.showToast(err)
    }

    override fun onTickersLoaded(tickers: ArrayList<Ticker>) {
        mainView.showTickers(tickers)
    }
}