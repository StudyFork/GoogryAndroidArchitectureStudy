package study.architecture.model.vo

import com.google.gson.annotations.SerializedName

data class Ticker(
    @SerializedName("market") val market: String, //코인명
    @SerializedName("trade_price") val tradePrice: Double, // 현재가
    @SerializedName("signed_change_rate") val signedChangeRate: Double, //전일대비
    @SerializedName("acc_trade_price_24h") val accTradePrice24h: Double //거래대금

)