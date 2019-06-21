package ado.sabgil.studyproject.ext

import ado.sabgil.studyproject.data.enums.BaseCurrency
import ado.sabgil.studyproject.data.model.Ticker
import android.widget.TextView
import androidx.databinding.BindingAdapter

private const val KILO = 1_000.0
private const val MEGA = 1_000_000.0

@BindingAdapter("setCurrentValue")
fun TextView.setCurrentValueByTicker(ticker: Ticker) {
    this.text =
        ticker.currentValue.let {
            when (ticker.base) {
                BaseCurrency.KRW.name ->
                    if (it - Math.floor(it) > 0) {
                        BaseCurrency.KRW.decimalPattern.format(it)
                    } else {
                        BaseCurrency.KRW.bigDecimalPattern.format(it)
                    }

                BaseCurrency.BTC.name ->
                    BaseCurrency.BTC.decimalPattern.format(it)

                BaseCurrency.ETH.name ->
                    BaseCurrency.ETH.decimalPattern.format(it)

                BaseCurrency.USDT.name ->
                    BaseCurrency.USDT.decimalPattern.format(it)

                else ->
                    BaseCurrency.DEFAULT.decimalPattern.format(it)
            }
        }
}

@BindingAdapter("setChangeRate")
fun TextView.setChangeRateByTicker(ticker: Ticker) {
    this.text = String.format("%s%%", (Math.floor(ticker.changeRate * 10_000.0) / 100.0))
}

@BindingAdapter("setAccTradeValue")
fun TextView.setAccTradeValueByTicker(ticker: Ticker) {
    this.text = when {
        ticker.accTradePrice > MEGA ->
            Math.floor(ticker.accTradePrice / MEGA).toInt().toString() + "M"
        ticker.accTradePrice > KILO ->
            Math.floor(ticker.accTradePrice / KILO).toInt().toString() + "K"
        else ->
            Math.floor(ticker.accTradePrice).toInt().toString()
    }
}