package sample.nackun.com.studyfirst.market

data class Ticker(
    val acc_trade_price_24h: Double,
    val change_price: Double,
    val market: String,
    val prev_closing_price: Double,
    val trade_price: Double
)