package com.example.seonoh.seonohapp.util

import android.content.Context
import android.util.Log
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.seonoh.seonohapp.R
import java.text.DecimalFormat

val doubleFormat = DecimalFormat("#,##0.00000000")
val intFormat = DecimalFormat("#,###.##")

object CalculateUtils {
    fun setMarketName(marketName: String): String? {
        return if (marketName?.contains("USDT")) {
             marketName?.substring(5, marketName.length)
        } else {
             marketName?.substring(4, marketName.length)
        }
    }

    fun filterTrade(tradePrice: Double?): String {
        return if (tradePrice ?: .0 > 1) {
            intFormat.format(tradePrice ?: 0).toString()
        } else {
            doubleFormat.format(tradePrice ?: 0).toString()
        }
    }

    //거래대금
    fun setTradeAmount(marketType: String, accTradePrice24h: Double, context: Context): String {
        var totalPriceAmount = accTradePrice24h.toLong()

        return when (marketType) {
            "KRW" -> {
                String.format(
                    context.getString(
                        when {
                            totalPriceAmount < 1_000_000L -> {
                                R.string.trade_amount_fmt
                            }
                            totalPriceAmount < 1_000_000_000_000L -> {
                                totalPriceAmount /= 1_000_000L
                                R.string.trade_amount_mega_fmt
                            }
                            else -> {
                                totalPriceAmount /= 1_000_000_000L
                                R.string.trade_amount_giga_fmt
                            }
                        }
                    )
                    , totalPriceAmount
                )
            }
            "BTC", "ETH" -> {
                String.format(context.getString(R.string.trade_amount_milli_fmt), accTradePrice24h)
            }
            "USDT" -> {
                String.format(
                    context.getString(
                        when {
                            totalPriceAmount < 1_000_000L -> {
                                R.string.trade_amount_fmt
                            }
                            totalPriceAmount < 1_000_000_000L -> {
                                totalPriceAmount /= 1_000L
                                R.string.trade_amount_kilo_fmt
                            }
                            totalPriceAmount < 1_000_000_000_000L -> {
                                totalPriceAmount /= 1_000_000L
                                R.string.trade_amount_mega_fmt
                            }
                            else -> {
                                totalPriceAmount /= 1_000_000_000L
                                R.string.trade_amount_giga_fmt
                            }
                        }
                    ), totalPriceAmount
                )
            }
            else -> {
                val fmtResId = when {
                    totalPriceAmount < 1_000L -> {
                        R.string.trade_amount_milli_fmt
                    }
                    totalPriceAmount < 1_000_000L -> {
                        R.string.trade_amount_fmt
                    }
                    totalPriceAmount < 1_000_000_000L -> {
                        totalPriceAmount /= 1_000L
                        R.string.trade_amount_kilo_fmt
                    }
                    totalPriceAmount < 1_000_000_000_000L -> {
                        totalPriceAmount /= 1_000_000L
                        R.string.trade_amount_mega_fmt
                    }
                    else -> {
                        totalPriceAmount /= 1_000_000_000L
                        R.string.trade_amount_giga_fmt
                    }
                }
                return if (totalPriceAmount < 1_000L) {
                    String.format(context.getString(fmtResId), accTradePrice24h)
                } else {
                    String.format(context.getString(fmtResId), totalPriceAmount)
                }
            }
        }
    }
}

//전일 대비
fun TextView.setTradeDiff(signedChangeRate: Double) {
    val color = when {
        (signedChangeRate > 0) -> ContextCompat.getColor(context, R.color.seonohRed)
        signedChangeRate < 0 -> ContextCompat.getColor(context, R.color.seonohBlue)
        else -> ContextCompat.getColor(context, R.color.seonohBlack)
    }
    setTextColor(color)

    val df = DecimalFormat("0.##")
    val rate = df.format(signedChangeRate * 100)
    Log.e("변화율","$rate")

    text = "$rate %"
}




