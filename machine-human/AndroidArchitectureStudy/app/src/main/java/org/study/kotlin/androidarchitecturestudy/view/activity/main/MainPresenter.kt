package org.study.kotlin.androidarchitecturestudy.view.activity.main

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
    var message: String

    constructor(
        view: MainContract.View,
        repository: BaseDataSource,
        getMessage: String
    ) {
        mainView = view
        tickerRemoteDataSource = repository
        message = getMessage
        tickerRemoteDataSource.requestMarkets(message, this)
    }

    override fun onTickerListLoaded(tickerList: ArrayList<TickerModel>) {
        mainView.setTickers(tickerList)

    }

    override fun onDataNotAvailable(error: Throwable) {
        mainView.showProgress(error)

    }

}