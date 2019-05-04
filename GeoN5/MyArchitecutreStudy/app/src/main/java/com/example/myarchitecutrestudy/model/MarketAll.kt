package com.example.myarchitecutrestudy.model

import com.google.gson.annotations.SerializedName

data class MarketAll(
    @SerializedName("market")
    val market: String, // - 기준으로 앞 : 타이틀, 뒤 : 코인명 , Ticker 조회 파라미터
    @SerializedName("korean_name")
    val koreanName: String,
    @SerializedName("english_name")
    val englishName: String
)