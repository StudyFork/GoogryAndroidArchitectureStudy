package com.example.seonoh.seonohapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Market(

    @Expose
    @SerializedName("market")
    val market: String,
    @Expose
    @SerializedName("korean_name")
    val koreanName: String,
    @Expose
    @SerializedName("english_name")
    val englishName: String
)