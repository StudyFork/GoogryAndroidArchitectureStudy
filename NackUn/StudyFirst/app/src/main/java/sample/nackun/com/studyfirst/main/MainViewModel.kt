package sample.nackun.com.studyfirst.main

import android.databinding.ObservableField
import android.databinding.ObservableList
import android.widget.TextView
import sample.nackun.com.studyfirst.Base.BaseViewModel
import sample.nackun.com.studyfirst.data.DataSource
import sample.nackun.com.studyfirst.data.Repository
import sample.nackun.com.studyfirst.util.TickerFormatter
import sample.nackun.com.studyfirst.vo.Ticker

class MainViewModel(
    val repository: Repository
) : BaseViewModel(),
    DataSource.RequestTickersCallback {

    var tickers = ObservableField<List<Map<String, String>>>()
    var errMsg = ObservableField<Throwable>()
    var selectedTextView = ObservableField<TextView>()

    init {
        tickers.set(mutableListOf())
    }

    override fun onError(t: Throwable) {
        errMsg.set(t)
    }

    override fun onTickersLoaded(tickers: List<Ticker>) {
        this.tickers.set(TickerFormatter.convertTo(tickers))
    }

    fun requestTickers(view: TextView) {
        selectedTextView.set(view)
        repository.requestMarkets(view.text.toString(), this)
    }
}