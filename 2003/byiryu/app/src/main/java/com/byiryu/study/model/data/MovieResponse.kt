package com.byiryu.study.model.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieResponse(

    @SerializedName("total")
    @Expose
    val total: Int,

    @SerializedName("start")
    @Expose
    val start: Int,

    @SerializedName("display")
    @Expose
    val display: Int,

    @SerializedName("lastBuildDate")
    @Expose
    val lastBuildDate: String,

    @SerializedName("items")
    @Expose
    val items: List<MovieItem>

)