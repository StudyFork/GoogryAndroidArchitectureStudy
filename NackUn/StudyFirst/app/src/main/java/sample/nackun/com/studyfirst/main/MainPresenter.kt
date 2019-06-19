package sample.nackun.com.studyfirst.main

import sample.nackun.com.studyfirst.data.Repository
import sample.nackun.com.studyfirst.vo.Ticker

class MainPresenter(val repository: Repository, val mainView: MainContract.View)
    : MainContract.Presenter{

    //lateinit var arr:ArrayList<Ticker>

    init {
        mainView.presenter = this
    }

    override fun start() {
        requestTickers()
    }

    override fun requestTickers(){
        repository.requestMarkets(mainView.marketName)
        mainView.showTickes(repository.tickers)
    }
}