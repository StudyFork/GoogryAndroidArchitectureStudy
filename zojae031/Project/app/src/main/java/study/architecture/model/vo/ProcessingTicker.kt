package study.architecture.model.vo

data class ProcessingTicker(

    val market: String, //코인명
    val tradePrice: String, // 현재가
    val changeRate: String, //전일대비
    val accTradePrice24h: String, //거래대금
    val color: Int

)