package sample.nackun.com.studyfirst.main

import sample.nackun.com.studyfirst.Base.BaseViewModel
import sample.nackun.com.studyfirst.data.DataSource
import sample.nackun.com.studyfirst.data.Repository
import sample.nackun.com.studyfirst.util.TickerFormatter
import sample.nackun.com.studyfirst.vo.Ticker

class MainViewModel(
    val view: MainFragment,
    val repository: Repository
) : BaseViewModel(),
    DataSource.RequestTickersCallback {

    fun requestTickers(marketName: String) =
        repository.requestMarkets(marketName, this)

    override fun onError(t: Throwable) =
        view.showMsg(t.message)

    override fun onTickersLoaded(tickers: List<Ticker>) =
        view.showTickers(TickerFormatter.convertTo(tickers))
}