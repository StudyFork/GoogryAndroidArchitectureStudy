package com.test.androidarchitecture.ui.ticker

import com.test.androidarchitecture.R
import com.test.androidarchitecture.data.Ticker
import com.test.androidarchitecture.data.TickerFormat
import com.test.androidarchitecture.data.source.UpbitRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class TickerPresenter(private val view: TickerContract.View) : TickerContract.Presenter {

    private val upbitRepository by lazy { UpbitRepository }
    private val disposables by lazy { CompositeDisposable() }


    override fun getTicker(marketSearch: String) {
        upbitRepository.getTicker(marketSearch)
            .map { list ->
                list.asSequence()
                    .map { getCoinFormat(it) }
                    .toList()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.setTickerData(it)
                }, {
                    view.showToast(it.message.toString())
                }
            )
            .apply { disposables.add(this) }
    }

    override fun disposablesClear() {
        disposables.clear()
    }

    private fun getCoinFormat(ticker: Ticker): TickerFormat {
        val marketName: String = ticker.market.substringAfter("-")

        val tradePrice: String = when {
            ticker.tradePrice < 1 -> String.format("%.8f", ticker.tradePrice)
            else -> NumberFormat.getNumberInstance(Locale.US).format(ticker.tradePrice)
        }

        val changeRate = "${String.format("%.2f", ticker.signedChangeRate * 100)}%"

        val changeColor: Int = when (ticker.change) {
            "RISE" -> R.color.colorRed
            "EVEN" -> R.color.colorBlack
            "FALL" -> R.color.colorBlue
            else -> R.color.colorBlack
        }
        val df = DecimalFormat("#,###")
        val accTradePrice: String = when {
            ticker.accTradePrice24h > 1000000 -> df.format(ticker.accTradePrice24h / 1000000) + "M"
            ticker.accTradePrice24h > 1000 -> df.format(ticker.accTradePrice24h / 1000) + "K"
            else -> df.format(ticker.accTradePrice24h)
        }
        return TickerFormat(marketName, tradePrice, changeRate, changeColor, accTradePrice)
    }

    companion object {

        @Volatile
        private var instance: TickerPresenter? = null

        @JvmStatic
        fun getInstance(view: TickerContract.View) = instance ?: synchronized(this) {
            instance ?: TickerPresenter(view).also {
                instance = it
            }
        }
    }
}