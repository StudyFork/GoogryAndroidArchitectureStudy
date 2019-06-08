package com.architecturestudy.model.upbit.response

import com.google.gson.annotations.SerializedName


data class MarketAll(
    @field:SerializedName("market")
    val market: String? = null,

    @field:SerializedName("korean_name")
    val koreanName: String? = null,

    @field:SerializedName("english_name")
    val englishName: String? = null
)