package com.android.studyfork.network.model

/**
 * created by onemask
 */
data class Ticker(
    val marketName : String, //코인명
    val currentTradePrice : Double, //현재가
    val beforeSignedChangeRate : Double, //전일대비
    val totalTrade : Double  //거래대금
)

interface TickerImpl{
    fun toTicker() : Ticker
}