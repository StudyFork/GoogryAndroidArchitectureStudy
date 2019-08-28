package com.jskim5923.architecturestudy.model

import com.google.gson.annotations.SerializedName
import com.jskim5923.architecturestudy.extension.getCoinName
import com.jskim5923.architecturestudy.util.DataFormatUtils

data class TickerResponse(
    @SerializedName("market")
    val market: String,
    @SerializedName("trade_price")
    val tradePrice: Double,
    @SerializedName("signed_change_rate")
    val signedChangeRate: Double,
    @SerializedName("acc_trade_price_24h")
    val accTradePrice24h: Double
) {
    fun toTicker(): Ticker {
        return Ticker(
            name = market.getCoinName(),
            currentPrice = DataFormatUtils.setCurrentPriceFormat(tradePrice),
            diff = DataFormatUtils.setSignedChangeRateFormat(signedChangeRate),
            diffTextColorId = if (signedChangeRate > 0) {
                android.R.color.holo_red_dark
            } else {
                android.R.color.holo_blue_light
            },
            tradeVolume = DataFormatUtils.setTradeVolumeText(market, accTradePrice24h)
        )
    }
}