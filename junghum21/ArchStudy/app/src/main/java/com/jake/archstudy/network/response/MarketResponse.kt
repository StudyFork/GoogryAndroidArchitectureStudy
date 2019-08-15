package com.jake.archstudy.network.response

import com.google.gson.annotations.SerializedName

data class MarketResponse(
    val market: String?,
    @SerializedName("korean_name")
    val koreanName: String?,
    @SerializedName("english_name")
    val englishName: String?
)