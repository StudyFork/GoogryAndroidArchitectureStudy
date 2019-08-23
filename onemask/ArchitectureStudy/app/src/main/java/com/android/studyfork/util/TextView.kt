
package com.android.studyfork.util

import android.widget.TextView
import androidx.core.content.ContextCompat
import com.android.studyfork.R
import com.android.studyfork.R.string.trade_amount_fmt
import com.android.studyfork.network.model.Ticker
import com.android.studyfork.util.TransactionAmount.Companion.giga
import com.android.studyfork.util.TransactionAmount.Companion.kilo
import com.android.studyfork.util.TransactionAmount.Companion.mega
import com.android.studyfork.util.TransactionAmount.Companion.tera
import java.text.DecimalFormat
import com.android.studyfork.R.string.trade_amount_milli_fmt as trade_amount_milli_fmt1

/**
 * created by onemask
 */

val doubleFormat = DecimalFormat("#,##0.00000000")
val intFormat = DecimalFormat("#,###.##")

//현재가
fun TextView.filterTrade(tradePrice: Double?){
    text = if(tradePrice ?:.0>1){
        intFormat.format(tradePrice ?:0)
    }else{
        doubleFormat.format(tradePrice ?:0)
    }
}

//전일 대비
fun TextView.setTradeDiff(signedChangeRate : Double){
    val color = when {
        (signedChangeRate > 0 )-> ContextCompat.getColor(context, R.color.red)
        signedChangeRate < 0 -> ContextCompat.getColor(context, R.color.blue)
        else -> ContextCompat.getColor(context, R.color.gray)
    }
    setTextColor(color)

    val df = DecimalFormat("0.##")
    val rate = df.format(signedChangeRate*100)
    text = "$rate%"
}

/*//거래대금
fun TextView.setTradeAmount(accTradePrice24h : Double) {

    val df = DecimalFormat("#,###")
    val amount: String = when {
        (accTradePrice24h >= 1000000) -> df.format(accTradePrice24h / 1000000)
        (accTradePrice24h < 1000000 && accTradePrice24h >= 1000) -> df.format(accTradePrice24h / 1000)
        else -> df.format(accTradePrice24h)
    }
    text="$amount"
}*/

//거래대금2
fun TextView.setTradeAmount(ticker: Ticker) {

    var amount = ticker.totalTrade.toLong()
    when (ticker.marketName.split("-")[0]) {
        "KRW" -> {
            text = String.format(
                context.getString(
                    when {
                        amount < mega -> {
                            trade_amount_fmt
                        }
                        amount < tera -> {
                            amount /= mega
                            R.string.trade_amount_mega_fmt
                        }
                        else -> {
                            amount /= giga
                            R.string.trade_amount_giga_fmt
                        }
                    }
                ), amount
            )
        }
        "BTC", "ETH" -> text =
            String.format(context.getString(trade_amount_milli_fmt1), ticker.totalTrade)
        "USDT" -> {
            text = String.format(
                context.getString(
                    when {
                        amount < mega -> {
                            trade_amount_fmt
                        }
                        amount < giga -> {
                            amount /= kilo
                            R.string.trade_amount_kilo_fmt
                        }
                        amount < tera -> {
                            amount /= mega
                            R.string.trade_amount_mega_fmt
                        }
                        else -> {
                            amount /= giga
                            R.string.trade_amount_giga_fmt
                        }
                    }
                ), amount
            )
        }
        else -> {
            val fmtResId: Any = when {
                amount < kilo -> {
                    R.string.trade_amount_milli_fmt

                }
                amount < mega -> {
                    trade_amount_fmt
                }
                amount < giga -> {
                    amount /= kilo
                    R.string.trade_amount_mega_fmt
                }
                amount < tera -> {
                    amount /= mega
                    R.string.trade_amount_mega_fmt
                }
                else -> {
                    amount /= giga
                    R.string.trade_amount_giga_fmt
                }
            }
            text = if (amount < kilo) {
                String.format(context.getString(fmtResId as Int), ticker.totalTrade)
            } else {
                String.format(context.getString(fmtResId as Int), amount)
            }
        }
    }
}

