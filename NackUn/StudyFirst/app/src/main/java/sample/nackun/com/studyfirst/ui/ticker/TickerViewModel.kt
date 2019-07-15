package sample.nackun.com.studyfirst.ui.ticker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import sample.nackun.com.studyfirst.base.BaseViewModel
import sample.nackun.com.studyfirst.data.Repository
import sample.nackun.com.studyfirst.util.TickerFormatter
import sample.nackun.com.studyfirst.vo.Ticker

class TickerViewModel(
    private val repository: Repository
) : BaseViewModel() {

    private val _tickers = MutableLiveData<List<Map<String, String>>>()
    val tickers: LiveData<List<Map<String, String>>> get() = _tickers
    private val _selectedMarket = MutableLiveData<String>()
    val selectedMarket: LiveData<String> get() = _selectedMarket
    private val _errMsg = MutableLiveData<Throwable>()
    val errMsg: LiveData<Throwable> get() = _errMsg

    init {
        _tickers.value = mutableListOf()
        _selectedMarket.value = "KRW"
    }

    private fun onError(t: Throwable) {
        _errMsg.value = t
    }

    private fun onTickersLoaded(tickers: List<Ticker>) {
        this._tickers.value = TickerFormatter.convertTo(tickers)
    }

    fun selectedMarket(marketLike: String) {
        _selectedMarket.value = marketLike
        showTickers(marketLike)
    }

    fun showTickers(marketLike: String?) {
        marketLike?.let {
            repository.requestMarkets(
                it,
                onTickersLoaded = {
                    onTickersLoaded(it)
                }, onError = {
                    onError(it)
                }
            )
        } ?: onError(IllegalStateException("Selected Market is not exist"))
    }
}