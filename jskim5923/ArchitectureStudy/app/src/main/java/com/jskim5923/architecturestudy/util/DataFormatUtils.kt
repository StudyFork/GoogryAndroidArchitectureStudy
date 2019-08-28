package com.jskim5923.architecturestudy.util

import com.jskim5923.architecturestudy.extension.getCoinCurrency
import com.jskim5923.architecturestudy.model.EnumCoinCurrency
import java.text.DecimalFormat

object DataFormatUtils {
    private const val COIN_PRICE_INTEGER_FORMAT = "#,###.##"

    private const val COIN_PRICE_DECIMAL_FORMAT = "#,##0.00000000"

    private const val COIN_CHANGE_RATE_FORMAT = "%.2f"

    private const val COIN_TRADE_VOLUME_FORMAT_DEFAULT = "%,d"

    private const val COIN_TRADE_VOLUME_FORMAT_DECIMAL = "%,.3f"

    private const val COIN_TRADE_VOLUME_FORMAT_KILO = "%,d K"

    private const val COIN_TRADE_VOLUME_FORMAT_MEGA = "%,d M"

    private const val COIN_TRADE_VOLUME_FORMAT_GIGA = "%,d G"


    fun setCurrentPriceFormat(currentPrice: Double): String {
        return if (currentPrice > 1) {
            DecimalFormat(COIN_PRICE_INTEGER_FORMAT).format(currentPrice)
        } else {
            DecimalFormat(COIN_PRICE_DECIMAL_FORMAT).format(currentPrice)
        }
    }

    fun setSignedChangeRateFormat(changeRate: Double): String {
        return "${COIN_CHANGE_RATE_FORMAT.format(changeRate * 100)}%"
    }

    fun setTradeVolumeText(market: String, accTradePrice24h: Double): String {
        return when (market.getCoinCurrency()) {
            EnumCoinCurrency.KRW.name, EnumCoinCurrency.USDT.name -> {
                var tradeVolume = accTradePrice24h.toLong()
                String.format(
                    when {
                        tradeVolume < 1_000_000L -> {
                            COIN_TRADE_VOLUME_FORMAT_DEFAULT
                        }
                        tradeVolume < 1_000_000_000L -> {
                            tradeVolume /= 1_000L
                            COIN_TRADE_VOLUME_FORMAT_KILO
                        }
                        tradeVolume < 1_000_000_000_000L -> {
                            tradeVolume /= 1_000_000L
                            COIN_TRADE_VOLUME_FORMAT_MEGA
                        }
                        else -> {
                            tradeVolume /= 1_000_000_000L
                            COIN_TRADE_VOLUME_FORMAT_GIGA
                        }
                    }, tradeVolume
                )
            }
            EnumCoinCurrency.BTC.name, EnumCoinCurrency.ETH.name -> {
                String.format(
                    COIN_TRADE_VOLUME_FORMAT_DECIMAL, accTradePrice24h
                )
            }
            else -> {
                var tradeVolume: Long = accTradePrice24h.toLong()
                String.format(
                    when {
                        tradeVolume < 1_000L -> {
                            COIN_TRADE_VOLUME_FORMAT_DECIMAL
                        }
                        tradeVolume < 1_000_000L -> {
                            COIN_TRADE_VOLUME_FORMAT_DEFAULT
                        }
                        tradeVolume < 1_000_000_000L -> {
                            tradeVolume /= 1_000L
                            COIN_TRADE_VOLUME_FORMAT_KILO
                        }
                        tradeVolume < 1_000_000_000_000L -> {
                            tradeVolume /= 1_000_000L
                            COIN_TRADE_VOLUME_FORMAT_MEGA
                        }
                        else -> {
                            tradeVolume /= 1_000_000_000L
                            COIN_TRADE_VOLUME_FORMAT_GIGA
                        }
                    }, tradeVolume
                )
            }
        }
    }

}