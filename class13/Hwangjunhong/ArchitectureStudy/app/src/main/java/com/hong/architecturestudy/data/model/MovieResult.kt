package com.hong.architecturestudy.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieResultData(
    @SerializedName("display")
    @Expose
    val display: Int,

    @SerializedName("items")
    @Expose
    val items: List<MovieData>,

    @SerializedName("lastBuildDate")
    @Expose
    val lastBuildDate: String,

    @SerializedName("start")
    @Expose
    val start: Int,

    @SerializedName("total")
    @Expose
    val total: Int
)