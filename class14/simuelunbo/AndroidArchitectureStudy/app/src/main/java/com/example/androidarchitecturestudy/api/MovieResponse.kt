package com.example.androidarchitecturestudy.api

import com.example.androidarchitecturestudy.model.Movie
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("display")
    val display: Int?,
    @SerializedName("items")
    val items: List<Movie>?,
    @SerializedName("lastBuildDate")
    val lastBuildDate: String?,
    @SerializedName("start")
    val start: Int?,
    @SerializedName("total")
    val total: Int?
)