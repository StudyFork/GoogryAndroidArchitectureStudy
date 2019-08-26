package com.example.architecturestudy.util

import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.architecturestudy.R
import java.text.DecimalFormat

val doubleFormat = DecimalFormat("#,##0.00000000")
val intFormat = DecimalFormat("#,###.##")

//현재가
fun TextView.filterTrade(tradePrice: Double?) {
    text = if (tradePrice ?: .0 > 1) {
        intFormat.format(tradePrice ?: 0)
    } else {
        doubleFormat.format(tradePrice ?: 0)
    }
}

//전일 대비
fun TextView.setTradeDiff(signedChangeRate: Double) {
    val color = when {
        (signedChangeRate > 0) -> ContextCompat.getColor(context, R.color.colorRed)
        signedChangeRate < 0 -> ContextCompat.getColor(context, R.color.colorBlue)
        else -> ContextCompat.getColor(context, R.color.colorGray)
    }
    setTextColor(color)

    val df = DecimalFormat("0.##")
    val rate = df.format(signedChangeRate * 100)
    text = "$rate%"
}

//거래대금
fun TextView.setTradeAmount(currency: String, accTradePrice24h: Double) {

    var amount = accTradePrice24h.toLong()
    when (currency) {
        "KRW" -> {
            text = String.format(
                context.getString(
                    when {
                        amount < 1_000_000L -> {
                            R.string.trade_amount_fmt
                        }
                        amount < 1_000_000_000_000L -> {
                            amount /= 1_000_000L
                            R.string.trade_amount_mega_fmt
                        }
                        else -> {
                            amount /= 1_000_000_000L
                            R.string.trade_amount_giga_fmt
                        }
                    }
                ), amount
            )
        }
        "BTC", "ETH" -> text = String.format(context.getString(R.string.trade_amount_milli_fmt), accTradePrice24h)
        "USDT" -> {
            text = String.format(
                context.getString(
                    when {
                        amount < 1_000_000L -> {
                            R.string.trade_amount_fmt
                        }
                        amount < 1_000_000_000L -> {
                            amount /= 1_000L
                            R.string.trade_amount_kilo_fmt
                        }
                        amount < 1_000_000_000_000L -> {
                            amount /= 1_000_000L
                            R.string.trade_amount_mega_fmt
                        }
                        else -> {
                            amount /= 1_000_000_000L
                            R.string.trade_amount_giga_fmt
                        }
                    }
                ), amount
            )
        }
        else -> {
            val fmtResId = when {
                amount < 1_000L -> {

                    R.string.trade_amount_milli_fmt
                }
                amount < 1_000_000L -> {
                    R.string.trade_amount_fmt
                }
                amount < 1_000_000_000L -> {
                    amount /= 1_000L
                    R.string.trade_amount_kilo_fmt
                }
                amount < 1_000_000_000_000L -> {
                    amount /= 1_000_000L
                    R.string.trade_amount_mega_fmt
                }
                else -> {
                    amount /= 1_000_000_000L
                    R.string.trade_amount_giga_fmt
                }
            }
            text = if (amount < 1_000L) {
                String.format(context.getString(fmtResId), accTradePrice24h)
            } else {
                String.format(context.getString(fmtResId), amount)
            }
        }
    }
}

fun TextView.setColor(color: Int){
    setTextColor(ContextCompat.getColor(context, color))
}