package dev.daeyeon.gaasproject.data

import com.google.gson.annotations.SerializedName

data class MarketResponse(
        @SerializedName("market") val market: String,
        @SerializedName("korean_name") val koreanName: String,
        @SerializedName("english_name") val englishName: String
)