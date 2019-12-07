package com.jay.architecturestudy.model

import com.google.gson.annotations.SerializedName

data class ResponseNaverQuery<T>(
    @SerializedName("lastBuildDate")
    val lastBuildDate: String,
    @SerializedName("total")
    val total: Int,
    @SerializedName("display")
    val display: Int,
    @SerializedName("items")
    val items: List<T>
)