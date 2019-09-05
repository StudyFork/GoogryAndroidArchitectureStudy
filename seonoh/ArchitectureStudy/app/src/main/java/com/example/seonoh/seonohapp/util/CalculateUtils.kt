package com.example.seonoh.seonohapp.util

import android.util.Log
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
    fun setTradeAmount(marketType: String, accTradePrice24h: Double): Map<String, Any> {
        var totalPriceAmount = accTradePrice24h.toLong()
        val mapValue = mutableMapOf<String, Any>()
        when (marketType) {
            "KRW" -> {
                when {
                    totalPriceAmount < 1_000_000L -> {
                        mapValue["format"] = R.string.trade_amount_fmt
                        mapValue["price"] = totalPriceAmount
                    }
                    totalPriceAmount < 1_000_000_000_000L -> {
                        totalPriceAmount /= 1_000_000L
                        mapValue["format"] = R.string.trade_amount_mega_fmt
                        mapValue["price"] = totalPriceAmount
                    }
                    else -> {
                        totalPriceAmount /= 1_000_000_000L
                        mapValue["format"] = R.string.trade_amount_giga_fmt
                        mapValue["price"] = totalPriceAmount

                    }
                }
            }
            "BTC", "ETH" -> {
                mapValue["format"] = R.string.trade_amount_milli_fmt
                mapValue["price"] = accTradePrice24h
            }
            "USDT" -> {
                when {
                    totalPriceAmount < 1_000_000L -> {
                        mapValue["format"] = R.string.trade_amount_fmt
                        mapValue["price"] = totalPriceAmount
                    }
                    totalPriceAmount < 1_000_000_000L -> {
                        totalPriceAmount /= 1_000L
                        mapValue["format"] = R.string.trade_amount_kilo_fmt
                        mapValue["price"] = totalPriceAmount
                    }
                    totalPriceAmount < 1_000_000_000_000L -> {
                        totalPriceAmount /= 1_000_000L
                        mapValue["format"] = R.string.trade_amount_mega_fmt
                        mapValue["price"] = totalPriceAmount
                    }
                    else -> {
                        totalPriceAmount /= 1_000_000_000L
                        mapValue["format"] = R.string.trade_amount_giga_fmt
                        mapValue["price"] = totalPriceAmount
                    }
                }
            }
            else -> {
                mapValue["format"] = when {
                    totalPriceAmount < 1_000L -> {
                        mapValue["format"] = R.string.trade_amount_milli_fmt
                    }
                    totalPriceAmount < 1_000_000L -> {
                        mapValue["format"] = R.string.trade_amount_fmt
                    }
                    totalPriceAmount < 1_000_000_000L -> {
                        totalPriceAmount /= 1_000L
                        mapValue["format"] = R.string.trade_amount_kilo_fmt
                    }
                    totalPriceAmount < 1_000_000_000_000L -> {
                        totalPriceAmount /= 1_000_000L
                        mapValue["format"] = R.string.trade_amount_mega_fmt

                    }
                    else -> {
                        totalPriceAmount /= 1_000_000_000L
                        mapValue["format"] = R.string.trade_amount_giga_fmt
                    }
                }
                if (totalPriceAmount < 1_000L) {
                    mapValue["price"] = accTradePrice24h
                } else {
                    mapValue["price"] = totalPriceAmount
                }

            }
        }
        return mapValue
    }

    fun setTradeDiff(signedChangeRate: Double): Map<String, Any> {
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