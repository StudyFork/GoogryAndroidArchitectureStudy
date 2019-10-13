package com.architecture.study.network.model

import android.graphics.Color
import androidx.core.content.ContextCompat
import com.architecture.study.App
import com.architecture.study.R
import com.architecture.study.data.model.Ticker
import com.google.gson.annotations.SerializedName
import java.text.DecimalFormat


data class TickerResponse(
    @SerializedName("acc_trade_price")
    val accTradePrice: Double,
    @SerializedName("acc_trade_price_24h")
    val accTradePrice24h: Double,
    @SerializedName("acc_trade_volume")
    val accTradeVolume: Double,
    @SerializedName("acc_trade_volume_24h")
    val accTradeVolume24h: Double,
    @SerializedName("change")
    val change: String,
    @SerializedName("change_price")
    val changePrice: Double,
    @SerializedName("change_rate")
    val changeRate: Double,
    @SerializedName("high_price")
    val highPrice: Double,
    @SerializedName("highest_52_week_date")
    val highest52WeekDate: String,
    @SerializedName("highest_52_week_price")
    val highest52WeekPrice: Double,
    @SerializedName("low_price")
    val lowPrice: Double,
    @SerializedName("lowest_52_week_date")
    val lowest52WeekDate: String,
    @SerializedName("lowest_52_week_price")
    val lowest52WeekPrice: Double,
    @SerializedName("market")
    val market: String,
    @SerializedName("opening_price")
    val openingPrice: Double,
    @SerializedName("prev_closing_price")
    val prevClosingPrice: Double,
    @SerializedName("signed_change_price")
    val signedChangePrice: Double,
    @SerializedName("signed_change_rate")
    val signedChangeRate: Double,
    @SerializedName("timestamp")
    val timestamp: Long,
    @SerializedName("trade_date")
    val tradeDate: String,
    @SerializedName("trade_date_kst")
    val tradeDateKst: String,
    @SerializedName("trade_price")
    val tradePrice: Double,
    @SerializedName("trade_time")
    val tradeTime: String,
    @SerializedName("trade_time_kst")
    val tradeTimeKst: String,
    @SerializedName("trade_timestamp")
    val tradeTimestamp: Long,
    @SerializedName("trade_volume")
    val tradeVolume: Double
) {
    fun toTicker(monetaryUnitList: List<String>): Ticker {
        val unitName = market.split("-")[0]
        val coinName = market.split("-")[1]
        val nowPrice = DecimalFormat("0.########").format(tradePrice)
        val compareYesterday: String
        val compareYesterdayTextColor: Int
        if (tradePrice > prevClosingPrice) {
            compareYesterday =
                DecimalFormat("0.##").format((1 - (prevClosingPrice / tradePrice)) * 10.0) + "%"
            compareYesterdayTextColor = R.color.colorRed
        } else {
            compareYesterday =
                "-" + DecimalFormat("0.##").format((1 - (tradePrice / prevClosingPrice)) * 10.0) + "%"
            compareYesterdayTextColor = R.color.colorBlue
        }
        val transactionAmount = when (market.split("-")[0]) {
            monetaryUnitList[0] -> {
                String.format("%,d", (accTradePrice24h / 1000000).toInt()) + "M"
            }
            monetaryUnitList[1] -> {
                String.format(
                    "%,d",
                    DecimalFormat("0.###").format(accTradePrice24h).split(".")[0].toInt()
                ) + if (DecimalFormat("0.###").format(accTradePrice24h).split(".").size > 1) {
                    "." + DecimalFormat("0.###").format(accTradePrice24h).split(".")[1]
                } else {
                    ""
                }
            }
            monetaryUnitList[2] -> {
                String.format(
                    "%,d",
                    DecimalFormat("0.###").format(accTradePrice24h).split(".")[0].toInt()
                ) + if (DecimalFormat("0.###").format(accTradePrice24h).split(".").size > 1) {
                    "." + DecimalFormat("0.###").format(accTradePrice24h).split(".")[1]
                } else {
                    ""
                }
            }
            monetaryUnitList[monetaryUnitList.lastIndex] -> {
                String.format(
                    "%,d",
                    DecimalFormat("0.###").format(accTradePrice24h / 1000).split(".")[0].toInt()
                ) + " k"
            }
            else -> error("error")
        }

        return Ticker(
            unitName,
            coinName,
            nowPrice,
            compareYesterday,
            compareYesterdayTextColor,
            transactionAmount
        )
    }
}