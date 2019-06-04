package my.gong.studygong.viewmodel

import my.gong.studygong.data.model.Ticker
import my.gong.studygong.data.source.upbit.UpbitDataSource
import kotlin.properties.Delegates

class CoinViewModel(
    private val upbitRepository: UpbitDataSource
) {

    private var tickerList: List<Ticker> by Delegates.observable(listOf()) { _, _, newValue ->
        tickerLoadedListener?.invoke(tickerList)
    }

    private var searchTickerResultList: List<Ticker> by Delegates.observable(listOf()) { _, _, newValue ->
        searchTickerLoadedListener?.invoke(searchTickerResultList)
    }

    private var errorMessage: String by Delegates.observable("") { _, _, newValue ->
        errorLoadedListener?.invoke(errorMessage)
    }

    private var baseCurrencyList: List<String> by Delegates.observable(listOf()) { _, _, newValue ->
        baseCurrencyLoadedListener?.invoke(baseCurrencyList)
    }

    var tickerLoadedListener: ((List<Ticker>) -> Unit)? = null
    var searchTickerLoadedListener: ((List<Ticker>) -> Unit)? = null
    var baseCurrencyLoadedListener: ((List<String>) -> Unit)? = null
    var errorLoadedListener: ((String) -> Unit)? = null

    fun loadTickerList(currency: String) {
        upbitRepository.getTickers(
            currency,
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
                searchTicker,
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