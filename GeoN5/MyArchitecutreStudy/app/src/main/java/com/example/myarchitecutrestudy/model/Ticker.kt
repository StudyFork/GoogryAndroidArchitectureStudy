package com.example.myarchitecutrestudy.model

data class Ticker(
    val acc_trade_price: Double,
    var acc_trade_price_24h: Double, //거래대금+M
    val acc_trade_volume: Double,
    val acc_trade_volume_24h: Double,
    val change: String,
    val change_price: Double,
    val change_rate: Double,
    val high_price: Double,
    val highest_52_week_date: String,
    val highest_52_week_price: Double,
    val low_price: Double,
    val lowest_52_week_date: String,
    val lowest_52_week_price: Double,
    val market: String,
    val opening_price: Double,
    val prev_closing_price: Double,
    val signed_change_price: Double,
    val signed_change_rate: Double, //전일대비+% 소수점2자리까지, 0보다 작으면 파랑,크면 빨강
    val timestamp: Long,
    val trade_date: String,
    val trade_date_kst: String,
    val trade_price: Double, //현재가[그대로]
    val trade_time: String,
    val trade_time_kst: String,
    val trade_timestamp: Long,
    val trade_volume: Double
)