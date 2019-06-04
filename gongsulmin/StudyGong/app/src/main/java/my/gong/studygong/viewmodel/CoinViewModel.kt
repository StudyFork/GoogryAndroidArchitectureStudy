package my.gong.studygong.viewmodel

import my.gong.studygong.data.model.Ticker
import my.gong.studygong.data.source.upbit.UpbitDataSource
import my.gong.studygong.ui.CoinListActivity
import java.util.*
import kotlin.properties.Delegates

class CoinViewModel(
    private val upbitRepository: UpbitDataSource
) {

    private var tickerList: List<Ticker> by Delegates.observable(listOf()) { _, _, newValue ->
        tickerLoadedListener?.invoke(newValue)
    }

    private var searchTickerResultList: List<Ticker> by Delegates.observable(listOf()) { _, _, newValue ->
        searchTickerLoadedListener?.invoke(newValue)
    }

    private var errorMessage: String by Delegates.observable("") { _, _, newValue ->
        errorLoadedListener?.invoke(newValue)
    }

    private var baseCurrencyList: List<String> by Delegates.observable(listOf()) { _, _, newValue ->
        baseCurrencyLoadedListener?.invoke(newValue)
    }

    var tickerLoadedListener: ((List<Ticker>) -> Unit)? = null
    var searchTickerLoadedListener: ((List<Ticker>) -> Unit)? = null
    var baseCurrencyLoadedListener: ((List<String>) -> Unit)? = null
    var errorLoadedListener: ((String) -> Unit)? = null

    private var timer: Timer = Timer()

    fun loadCoin(coinMarket: String = "KRW") {
        timer.cancel()
        timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                loadTickerList(coinMarket)
            }
        }, 0, CoinListActivity.REPEAT_INTERVAL_MILLIS)
    }

    fun onStop(){
        timer.cancel()
    }

    fun loadTickerList(currency: String) {
        upbitRepository.getTickers(
            tickerCurrency = currency,
            success = {
                tickerList = it
            },
            fail = {
                errorMessage = it
            }
        )
    }

    fun loadTickerSearchResult(searchTicker: String) {
        if (searchTicker.isNotEmpty()) {
            upbitRepository.getDetailTickers(
                tickerSearch = searchTicker,
                success = {
                    searchTickerResultList = it
                },
                fail = {
                    errorMessage = it
                }
            )
        } else {
            errorMessage = "검색한 코인은 찾을수 없습니다!"
        }
    }

    fun loadBaseCurrency() {
        upbitRepository.getCoinCurrency(
            success = {
                baseCurrencyList = it
            },
            fail = {
                errorMessage = it
            }
        )
    }
}