package sample.nackun.com.studyfirst.market


import com.google.gson.annotations.SerializedName

data class Ticker(
    @SerializedName("acc_trade_price_24h")
    val accTradePrice24h: Double,
    @SerializedName("change_price")
    val changePrice: Double,
    @SerializedName("market")
    val market: String,
    @SerializedName("prev_closing_price")
    val prevClosingPrice: Double,
    @SerializedName("trade_price")
    val tradePrice: Double
)