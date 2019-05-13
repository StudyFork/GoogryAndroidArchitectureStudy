package my.gong.studygong.data.model

data class Ticker(
    val market: String,
    val tradePrice: String,
    val changeRate: String,
    val accTradePrice24h: String
)