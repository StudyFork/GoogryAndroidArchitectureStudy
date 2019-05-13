package my.gong.studygong.data.source.upbit

import my.gong.studygong.data.model.Ticker
import my.gong.studygong.data.model.enum.TickerCurrency

interface UpbitDataSource {
    fun getTickers(
        tickerCurrency: TickerCurrency,
        success: (List<Ticker>) -> Unit,
        fail: (String) -> Unit
    )
}