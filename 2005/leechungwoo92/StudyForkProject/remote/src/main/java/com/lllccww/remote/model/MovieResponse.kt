package com.lllccww.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lllccww.remote.MovieItem

data class MovieResponse(
    @SerializedName("display")
    @Expose
    val display: String,

    @SerializedName("lastBuildDate")
    @Expose
    val lastBuildDate: String,

    @SerializedName("start")
    @Expose
    val start: String,

    @SerializedName("total")
    @Expose
    val total: String,

    @SerializedName("items")
    @Expose
    val items: ArrayList<MovieItem>
)
