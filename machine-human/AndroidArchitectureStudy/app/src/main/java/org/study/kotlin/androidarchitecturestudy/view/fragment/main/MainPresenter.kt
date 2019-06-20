package org.study.kotlin.androidarchitecturestudy.view.fragment.main

import android.util.Log
import org.study.kotlin.androidarchitecturestudy.api.model.TickerModel
import org.study.kotlin.androidarchitecturestudy.base.BaseDataSource
/**
***************************
structure

i = interface
f = function
v = variable
***************************

i = MainContract

    i = View : BaseView<Presenter>

        f = setTickers(tickers: ArrayList<TickerModel>)

    i = Presenter : BasePresenter

        f = requestDataFromTickerRepository(marketName: String)



i = BaseDataSource

    i = GetTickerListCallback

        f = onTickerListLoaded(tickerList: ArrayList<TickerModel>)
        f = onDataNotAvailable(error: String)

    f = requestMarkets(marketName: String, callback: GetTickerListCallback)

 */
class MainPresenter : MainContract.Presenter, BaseDataSource.GetTickerListCallback {
    var mainView: MainContract.View
    var tickerRemoteDataSource: BaseDataSource

    constructor(
        view: MainContract.View,
        repository: BaseDataSource
    ) {
        mainView = view
        tickerRemoteDataSource = repository
    }

    override fun start() {
        mainView.presenter = this
        Log.e("TAG MainPresenter", "start")

    }

    override fun requestDataFromTickerRepository(marketName: String) {
        tickerRemoteDataSource.requestMarkets(marketName, this)
        Log.e("TAG MainPresenter", "requestDataFromTickerRepository")

    }

    override fun onTickerListLoaded(tickerList: ArrayList<TickerModel>) {
        mainView.setTickers(tickerList)
        Log.e("TAG MainPresenter", "onTickerListLoaded")

    }

    override fun onDataNotAvailable(error: String) {
        mainView.showProgress(error)
        Log.e("TAG MainPresenter", "onDataNotAvailable")

    }

}