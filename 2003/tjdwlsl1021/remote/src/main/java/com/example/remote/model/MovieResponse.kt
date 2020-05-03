package com.example.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("lastBuildDate")
    @Expose
    val lastBuildDate: String,
    @SerializedName("total")
    @Expose
    val total: Int,
    @SerializedName("start")
    @Expose
    val start: Int,
    @SerializedName("display")
    @Expose
    val display: Int,
    @SerializedName("items")
    @Expose
    val items: List<Movie>
)