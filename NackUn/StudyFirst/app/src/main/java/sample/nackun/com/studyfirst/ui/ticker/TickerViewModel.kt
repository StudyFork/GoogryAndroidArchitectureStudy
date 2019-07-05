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
        repository.requestMarkets(view.text.toString(), this)
    }

    fun showTickers(view: View) {
        if (view is TextView) {
            selectedTextView.set(view)
            repository.requestMarkets(view.text.toString(), this)
        }
    }
}