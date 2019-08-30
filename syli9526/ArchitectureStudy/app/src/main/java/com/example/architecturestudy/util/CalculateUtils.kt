package com.example.architecturestudy.util

import com.example.architecturestudy.R
import java.text.DecimalFormat

object CalculateUtils {

    private val doubleFormat = DecimalFormat("#,##0.00000000")
    private val intFormat = DecimalFormat("#,###.##")

    fun filterTrade(tradePrice: Double?): String {
        return if (tradePrice ?: .0 > 1) {
            intFormat.format(tradePrice ?: 0).toString()
        } else {
            doubleFormat.format(tradePrice ?: 0).toString()
        }
    }

    //거래대금
    fun setTradeAmount(marketType: String, accTradePrice24h: Double): String {
        var totalPriceAmount = accTradePrice24h.toLong()

        return when (marketType) {
            "KRW" -> {
                String.format(
                    when {
                        totalPriceAmount < 1_000_000L -> {
                            "%,d"
                        }
                        totalPriceAmount < 1_000_000_000_000L -> {
                            totalPriceAmount /= 1_000_000L
                            "%,d M"
                        }
                        else -> {
                            totalPriceAmount /= 1_000_000_000L
                            "%,d G"
                        }
                    }
                    , totalPriceAmount
                )
            }
            "BTC", "ETH" -> {
                String.format("%,.3f", accTradePrice24h)
            }
            "USDT" -> {
                String.format(
                    when {
                        totalPriceAmount < 1_000_000L -> {
                            "%,d"
                        }
                        totalPriceAmount < 1_000_000_000L -> {
                            totalPriceAmount /= 1_000L
                            "%,d k"
                        }
                        totalPriceAmount < 1_000_000_000_000L -> {
                            totalPriceAmount /= 1_000_000L
                            "%,d M"
                        }
                        else -> {
                            totalPriceAmount /= 1_000_000_000L
                            "%,d G"
                        }
                    }
                    , totalPriceAmount
                )
            }
            else -> {
                val fmtResId = when {
                    totalPriceAmount < 1_000L -> {
                        "%,.3f"
                    }
                    totalPriceAmount < 1_000_000L -> {
                        "%,d"
                    }
                    totalPriceAmount < 1_000_000_000L -> {
                        totalPriceAmount /= 1_000L
                        "%,d k"
                    }
                    totalPriceAmount < 1_000_000_000_000L -> {
                        totalPriceAmount /= 1_000_000L
                        "%,d M"
                    }
                    else -> {
                        totalPriceAmount /= 1_000_000_000L
                        "%,d G"
                    }
                }
                return if (totalPriceAmount < 1_000L) {
                    String.format(fmtResId, accTradePrice24h)
                } else {
                    String.format(fmtResId, totalPriceAmount)
                }
            }
        }
    }

    //전일 대비
    fun setTradeDiff(signedChangeRate: Double): String {

        val df = DecimalFormat("0.##")
        val rate = df.format(signedChangeRate * 100)

        return "$rate%"

    }

    fun setColor(signedChangeRate: Double): Int {
        return when {
            (signedChangeRate > 0) -> R.color.colorRed
            signedChangeRate < 0 -> R.color.colorBlue
            else -> R.color.colorGray
        }

    }

}



