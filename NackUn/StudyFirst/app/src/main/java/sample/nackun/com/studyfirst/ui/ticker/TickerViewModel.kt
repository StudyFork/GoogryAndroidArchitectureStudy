package sample.nackun.com.studyfirst.ui.ticker

import android.databinding.ObservableField
import sample.nackun.com.studyfirst.base.BaseViewModel
import sample.nackun.com.studyfirst.data.Repository
import sample.nackun.com.studyfirst.util.TickerFormatter
import sample.nackun.com.studyfirst.vo.Ticker

class TickerViewModel(
    private val repository: Repository
) : BaseViewModel() {

    val tickers = ObservableField<List<Map<String, String>>>(mutableListOf())
    val selectedMarket = ObservableField("KRW")
    val errMsg = ObservableField<Throwable>()

    private fun onError(t: Throwable) {
        errMsg.set(t)
    }

    private fun onTickersLoaded(tickers: List<Ticker>) {
        this.tickers.set(TickerFormatter.convertTo(tickers))
    }

    fun selectedMarket(marketLike: String) {
        selectedMarket.set(marketLike)
        showTickers(marketLike)
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