package com.example.seonoh.seonohapp.model

import com.google.gson.annotations.SerializedName

data class Market(

    @SerializedName("market")
    val market: String,
    @SerializedName("korean_name")
    val koreanName: String,
    @SerializedName("english_name")
    val englishName: String
)