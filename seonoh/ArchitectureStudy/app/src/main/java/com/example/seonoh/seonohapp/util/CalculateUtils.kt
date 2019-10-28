package com.example.seonoh.seonohapp.util

import android.util.Log
import com.example.seonoh.seonohapp.R
import java.text.DecimalFormat

val doubleFormat = DecimalFormat("#,##0.00000000")
val intFormat = DecimalFormat("#,###.##")

object CalculateUtils {
    fun setMarketName(
        marketName: String
    ): String? {
        return if (marketName?.contains("USDT")) {
            marketName?.substring(5, marketName.length)
        } else {
            marketName?.substring(4, marketName.length)
        }
    }

    fun filterTrade(
        tradePrice: Double?
    ): String {
        return if (tradePrice ?: .0 > 1) {
            intFormat.format(tradePrice ?: 0).toString()
        } else {
            doubleFormat.format(tradePrice ?: 0).toString()
        }

    }

    //거래대금
    fun setTradeAmount(
        marketType: String,
        accTradePrice24h: Double
    ): Map<String, Number> {
        val totalPriceAmount = accTradePrice24h.toLong()
        return when (marketType) {
            "KRW" -> {
                when {
                    totalPriceAmount < 1_000_000L -> {
                        mapOf<String, Number>(
                            "format" to R.string.trade_amount_fmt,
                            "price" to totalPriceAmount
                        )
                    }
                    totalPriceAmount < 1_000_000_000_000L -> {
                        mapOf<String, Number>(
                            "format" to R.string.trade_amount_mega_fmt,
                            "price" to totalPriceAmount / 1_000_000L
                        )
                    }
                    else -> {
                        mapOf<String, Number>(
                            "format" to R.string.trade_amount_giga_fmt,
                            "price" to totalPriceAmount / 1_000_000_000L
                        )
                    }
                }
            }
            "BTC", "ETH" -> {
                mapOf(
                    "format" to R.string.trade_amount_milli_fmt,
                    "price" to accTradePrice24h
                )
            }
            "USDT" -> {
                when {
                    totalPriceAmount < 1_000_000L -> {
                        mapOf<String, Number>(
                            "format" to R.string.trade_amount_fmt,
                            "price" to totalPriceAmount
                        )
                    }
                    totalPriceAmount < 1_000_000_000L -> {
                        mapOf<String, Number>(
                            "format" to R.string.trade_amount_kilo_fmt,
                            "price" to totalPriceAmount / 1_000L
                        )
                    }
                    totalPriceAmount < 1_000_000_000_000L -> {
                        mapOf<String, Number>(
                            "format" to R.string.trade_amount_mega_fmt,
                            "price" to totalPriceAmount / 1_000_000L
                        )
                    }
                    else -> {
                        mapOf<String, Number>(
                            "format" to R.string.trade_amount_giga_fmt,
                            "price" to totalPriceAmount / 1_000_000_000L
                        )
                    }
                }
            }
            else -> {
                when {
                    totalPriceAmount < 1_000L -> {
                        mapOf<String, Number>(
                            "format" to R.string.trade_amount_milli_fmt,
                            "price" to accTradePrice24h
                        )
                    }
                    totalPriceAmount < 1_000_000L -> {
                        mapOf<String, Number>(
                            "format" to R.string.trade_amount_fmt,
                            "price" to totalPriceAmount
                        )
                    }
                    totalPriceAmount < 1_000_000_000L -> {
                        mapOf<String, Number>(
                            "format" to R.string.trade_amount_kilo_fmt,
                            "price" to totalPriceAmount / 1_000L
                        )
                    }
                    totalPriceAmount < 1_000_000_000_000L -> {
                        mapOf<String, Number>(
                            "format" to R.string.trade_amount_mega_fmt,
                            "price" to totalPriceAmount / 1_000_000L
                        )
                    }
                    else -> {
                        mapOf<String, Number>(
                            "format" to R.string.trade_amount_giga_fmt,
                            "price" to totalPriceAmount / 1_000_000_000L
                        )
                    }
                }
            }
        }
    }

    fun setTradeDiff(
        signedChangeRate: Double
    ): Map<String, Any> {
        val mapValue = mutableMapOf<String, Any>()
        val color = when {
            (signedChangeRate > 0) -> R.color.seonohRed
            signedChangeRate < 0 -> R.color.seonohBlue
            else -> R.color.seonohBlack
        }

        val df = DecimalFormat("0.##")
        val rate = df.format(signedChangeRate * 100)
        Log.e("변화율", "$rate")

        mapValue["color"] = color
        mapValue["rate"] = "$rate %"
        return mapValue
    }
}