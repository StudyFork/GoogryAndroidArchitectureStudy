package my.gong.studygong.data.source.upbit

import my.gong.studygong.data.model.Ticker
import my.gong.studygong.data.model.enum.TickerCurrency

interface IUpbitDataSource {
    interface callBack{
        var success: (List<Ticker>) -> Unit
        var fail: (String) -> Unit
    }
    fun getTickers(
        tickerCurrency: TickerCurrency ,
        success: (List<Ticker>) -> Unit,
        fail: (String) -> Unit
    )

}