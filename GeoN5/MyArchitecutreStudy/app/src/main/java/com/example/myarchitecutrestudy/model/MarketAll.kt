package com.example.myarchitecutrestudy.model

data class MarketAll(
    val english_name: String,
    val korean_name: String,
    val market: String // - 기준으로 앞 : 타이틀, 뒤 : 코인명 , Ticker 조회 파라미터
)