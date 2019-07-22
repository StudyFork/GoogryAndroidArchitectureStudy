package com.architecturestudy.util

import com.architecturestudy.data.upbit.UpbitTicker
import java.math.BigDecimal
import java.text.DecimalFormat

object NumberFormatter {
    private val millionFormat = DecimalFormat("#,###M")
    private val thousandFormat = DecimalFormat("#,###k")
    private val normalFormat = DecimalFormat("#,###.##")

    fun convertTo(target: List<UpbitTicker>): List<Map<String, String>> {
        val convertList = mutableListOf<Map<String, String>>()
        convertList.clear()

        target.forEachIndexed { index, upbitTicker ->
            convertList.add(
                index,
                hashMapOf(
                    "prefix" to upbitTicker.prefix,
                    "market" to convertToMarket(upbitTicker.market),
                    "tradePrice" to convertToTradePrice(upbitTicker.tradePrice),
                    "signedChangeRate" to convertToSignedChangeRate(upbitTicker.signedChangeRate),
                    "accTradePrice24h" to convertToAccTradePrice24h(upbitTicker.accTradePrice24h)
                )
            )
        }
        return convertList
    }

    private fun convertToMarket(market: String?): String {
        market?.let {
            return if (it.contains("-")) {
                it.substringAfter("-")
            } else {
                it
            }
        }
        return ""
    }

    private fun convertToTradePrice(tradePrice: Double?): String {
        tradePrice?.let {
            return if (it.toBigDecimal() < BigDecimal.ONE) {
                String.format("%.8f", it.toBigDecimal())
            } else {
                normalFormat.format(it.toBigDecimal())
            }
        }
        return ""
    }

    private fun convertToSignedChangeRate(signedChangeRate: Double?): String {
        signedChangeRate?.let {
            return String.format("%.2f", it * 100)
        }
        return ""
    }

    private fun convertToAccTradePrice24h(accTradePrice24h: Double?): String {
        accTradePrice24h?.let {
            return when {
                it / 1_000_000 > 100 -> millionFormat.format(it / 1_000_000)
                it / 1_000 > 1_000 -> thousandFormat.format(it / 1_000)
                else -> normalFormat.format(it)
            }
        }
        return ""
    }
}