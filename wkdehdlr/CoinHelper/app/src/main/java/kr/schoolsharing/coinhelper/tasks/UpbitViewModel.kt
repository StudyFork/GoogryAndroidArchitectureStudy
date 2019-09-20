package kr.schoolsharing.coinhelper.tasks

import android.util.Log
import androidx.databinding.ObservableField
import kr.schoolsharing.coinhelper.data.Repository
import kr.schoolsharing.coinhelper.data.UpbitDataSource
import kr.schoolsharing.coinhelper.model.UpbitItem
import kr.schoolsharing.coinhelper.model.UpbitMarket
import kr.schoolsharing.coinhelper.model.UpbitTicker
import kr.schoolsharing.coinhelper.util.TextEditor

class UpbitViewModel(val repository: Repository) {

    private var tickerList = ObservableField<List<UpbitItem>>()

    fun loadUpbitMarket(marketName: String) {
        repository.getMarket(object : UpbitDataSource.GetMarketCallback {
            override fun onMarketLoaded(markets: List<UpbitMarket>) {
                val marketList = markets
                    .filter { TextEditor.splitString(it.market, 0) == marketName }
                    .joinToString(",") { it.market }
                loadUpbitTicker(marketList)
            }

            override fun onDataNotAvailable(t: Throwable) {
                Log.e("UpbitViewModel/loadUpbitMarket : ", t.toString())
//                upbitView.showErrorToast(t.printStackTrace().toString())
            }
        })
    }

    private fun loadUpbitTicker(marketList: String) {
        Repository.getTicker(marketList, object : UpbitDataSource.GetTickerCallback {

            override fun onTickerLoaded(tickers: List<UpbitTicker>) {

                tickerList.set(tickers.map {
                    UpbitItem(
                        TextEditor.splitString(it.market, 1),
                        TextEditor.makeTradePrice(it.tradePrice),
                        it.change,
                        TextEditor.makeSignedChangeRate(it.signedChangePrice),
                        TextEditor.makeAccTradePrice24h(it.accTradePrice24h)
                    )
                })
            }

            override fun onDataNotAvailable(t: Throwable) {
                Log.e("UpbitViewModel/loadUpbitTicker : ", t.toString())
//                upbitView.showErrorToast(t.printStackTrace().toString())
            }
        })
    }

}