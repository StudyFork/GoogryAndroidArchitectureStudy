package com.jake.archstudy.data.model

import com.google.gson.annotations.SerializedName

data class Market(
    val market: String?,
    @SerializedName("korean_name")
    val koreanName: String?,
    @SerializedName("english_name")
    val englishName: String?
)