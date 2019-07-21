package com.android.studyfork.repository.model

import com.google.gson.annotations.SerializedName

data class MarketAllResponse(
    @SerializedName("market") val market: String,
    @SerializedName("korean_name") val koreanName: String,
    @SerializedName("english_name") val englishName: String
)