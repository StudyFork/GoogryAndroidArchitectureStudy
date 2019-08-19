package com.test.androidarchitecture.data

import com.google.gson.annotations.SerializedName

data class Market(

    @SerializedName("english_name")
    val englishName: String,

    @SerializedName("korean_name")
    val koreanName: String,

    val market: String
)