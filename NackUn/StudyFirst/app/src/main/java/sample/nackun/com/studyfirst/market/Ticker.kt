package sample.nackun.com.studyfirst.market

data class Ticker(
    val accTradePrice24h: Double,
    val changePrice: Double,
    val market: String,
    val prevClosingPrice: Double,
    val tradePrice: Double
)