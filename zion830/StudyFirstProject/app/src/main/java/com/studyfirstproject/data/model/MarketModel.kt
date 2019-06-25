package com.studyfirstproject.data.model


import com.google.gson.annotations.SerializedName

data class MarketModel(
    @SerializedName("english_name")
    val englishName: String,
    @SerializedName("korean_name")
    val koreanName: String,
    @SerializedName("market")
    val market: String
)