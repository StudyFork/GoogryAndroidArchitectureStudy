package study.architecture.vo

data class Ticker(

    val market: String,
    val trade_price: Double,
    val change_rate: Double,
    val acc_trade_price_24h: Double

)