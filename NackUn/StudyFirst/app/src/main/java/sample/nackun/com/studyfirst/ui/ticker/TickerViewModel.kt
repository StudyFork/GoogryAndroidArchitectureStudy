package sample.nackun.com.studyfirst.ui.ticker

import android.databinding.ObservableField
import android.view.View
import android.widget.TextView
import sample.nackun.com.studyfirst.base.BaseViewModel
import sample.nackun.com.studyfirst.data.DataSource
import sample.nackun.com.studyfirst.data.Repository
import sample.nackun.com.studyfirst.util.TickerFormatter
import sample.nackun.com.studyfirst.vo.Ticker

class TickerViewModel(
    private val repository: Repository
) : BaseViewModel() {

    val tickers = ObservableField<List<Map<String, String>>>(mutableListOf())
    val selectedMarket = ObservableField<String>("KRW")
    val errMsg = ObservableField<Throwable>()

    private fun onError(t: Throwable) {
        errMsg.set(t)
    }

    private fun onTickersLoaded(tickers: List<Ticker>) {
        this.tickers.set(TickerFormatter.convertTo(tickers))
    }

    fun selectedMarket(view: View) {
        if (view is TextView) {
            selectedMarket.set(view.text.toString())
            showTickers(view.text.toString())
        }
    }

    fun showTickers(marketLike: String) {
        repository.requestMarkets(
            marketLike,
            onTickersLoaded = {
                onTickersLoaded(it)
            }, onError = {
                onError(it)
            }
        )
    }
}