package study.architecture.myarchitecture.ui.model

import study.architecture.myarchitecture.data.model.UpbitTicker
import study.architecture.myarchitecture.util.TextUtil

data class TickerItem(
    //코인명
    val coinName: String,

    //현재가
    val tradePrice: Double,
    val last: String,

    //전일대비
    val signedChangeRate: Double,
    val tradeDiff: String,

    //거래대금
    val accTradePrice24h: Double,
    val tradeAmount: String
)

fun UpbitTicker.mapToPresentation(): TickerItem = TickerItem(
    TextUtil.getCoinName(market),
    tradePrice,
    TextUtil.getLast(tradePrice),
    signedChangeRate,
    TextUtil.getTradeDiff(signedChangeRate),
    accTradePrice24h,
    TextUtil.getTradeAmount(accTradePrice24h)
)

fun List<UpbitTicker>.mapToPresentation(): List<TickerItem> = map { it.mapToPresentation() }