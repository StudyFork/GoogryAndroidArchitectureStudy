package sample.nackun.com.studyfirst.ui.ticker

import android.databinding.BaseObservable
import android.databinding.ObservableField
import android.view.View
import android.widget.TextView
import org.w3c.dom.Text
import sample.nackun.com.studyfirst.data.DataSource
import sample.nackun.com.studyfirst.data.Repository
import sample.nackun.com.studyfirst.util.TickerFormatter
import sample.nackun.com.studyfirst.vo.Ticker

class TickerViewModel(
    private val repository: Repository
) : BaseObservable(), DataSource.RequestTickersCallback {

    var tickers = ObservableField<List<Map<String, String>>>()
    var selectedMarket = ObservableField<String>()
    var errMsg = ObservableField<Throwable>()

    init {
        tickers.set(mutableListOf())
        selectedMarket.set("KRW")
    }

    override fun onError(t: Throwable) {
        errMsg.set(t)
    }

    override fun onTickersLoaded(tickers: List<Ticker>) {
        this.tickers.set(TickerFormatter.convertTo(tickers))
    }

    fun selectedMarket(view: View) {
        if (view is TextView) {
            selectedMarket.set(view.text.toString())
            showTickers(view)
        }
    }

    fun showTickers(view: TextView) {
        repository.requestMarkets(view.text.toString(), this)
    }
}