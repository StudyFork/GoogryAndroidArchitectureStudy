package com.jskim5923.architecturestudy.extension

import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.jskim5923.architecturestudy.R
import com.jskim5923.architecturestudy.model.EnumCoinCurrency
import com.jskim5923.architecturestudy.model.Ticker
import java.text.DecimalFormat

fun TextView.setCurrentPriceText(currentPrice: Double) {
    text = if (currentPrice > 1) {
        DecimalFormat(context.getString(R.string.coin_price_integer_format)).format(currentPrice)
    } else {
        DecimalFormat(context.getString(R.string.coin_price_decimal_format)).format(currentPrice)
    }
}

fun TextView.setSignedChangeRateText(changeRate: Double) {
    if (changeRate > 0) {
        setTextColor(
            ResourcesCompat.getColor(
                resources,
                android.R.color.holo_red_dark,
                context.theme
            )
        )
    } else {
        setTextColor(
            ResourcesCompat.getColor(
                resources,
                android.R.color.holo_blue_light,
                context.theme
            )
        )
    }

    text = String.format(context.getString(R.string.coin_change_rate_format), changeRate * 100)
}

fun TextView.setTradeVolumeText(ticker: Ticker) {
    when (ticker.market.getCoinCurrency()) {
        EnumCoinCurrency.KRW.name, EnumCoinCurrency.USDT.name -> {
            var tradeVolume: Long = ticker.acc_trade_price_24h.toLong()
            text = String.format(
                context.getString(
                    when {
                        tradeVolume < 1_000_000L -> {
                            R.string.coin_trade_volume_format_default
                        }
                        tradeVolume < 1_000_000_000L -> {
                            tradeVolume /= 1_000L
                            R.string.coin_trade_volume_format_kilo
                        }
                        tradeVolume < 1_000_000_000_000L -> {
                            tradeVolume /= 1_000_000L
                            R.string.coin_trade_volume_format_mega
                        }
                        else -> {
                            tradeVolume /= 1_000_000_000L
                            R.string.coin_trade_volume_format_giga
                        }
                    }
                ), tradeVolume
            )
        }
        EnumCoinCurrency.BTC.name, EnumCoinCurrency.ETH.name -> {
            text = String.format(
                context.getString(
                    R.string.coin_trade_volume_format_decimal,
                    ticker.acc_trade_price_24h
                )
            )
        }
        else -> {
            var tradeVolume: Long = ticker.acc_trade_price_24h.toLong()
            text = String.format(
                context.getString(
                    when {
                        tradeVolume < 1_000L -> {
                            R.string.coin_trade_volume_format_decimal
                        }
                        tradeVolume < 1_000_000L -> {
                            R.string.coin_trade_volume_format_default
                        }
                        tradeVolume < 1_000_000_000L -> {
                            tradeVolume /= 1_000L
                            R.string.coin_trade_volume_format_kilo
                        }
                        tradeVolume < 1_000_000_000_000L -> {
                            tradeVolume /= 1_000_000L
                            R.string.coin_trade_volume_format_mega
                        }
                        else -> {
                            tradeVolume /= 1_000_000_000L
                            R.string.coin_trade_volume_format_giga
                        }
                    }, tradeVolume
                )
            )
        }
    }
}