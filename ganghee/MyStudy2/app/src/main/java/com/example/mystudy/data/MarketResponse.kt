package com.example.mystudy.data


import com.google.gson.annotations.SerializedName

data class MarketResponse(
    @SerializedName("english_name")
    val englishName: String,
    @SerializedName("korean_name")
    val koreanName: String,
    @SerializedName("market")
    val market: String
)