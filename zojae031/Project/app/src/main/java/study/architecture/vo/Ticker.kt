package study.architecture.vo

data class Ticker(

    val market: String, //코인명
    val trade_price: Double, // 현재가
    val change_rate: Double, //전일대비
    val acc_trade_price_24h: Double //거래대금

)