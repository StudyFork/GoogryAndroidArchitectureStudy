package com.mtjin.data.model.search
import com.google.gson.annotations.SerializedName
import com.mtjin.data.model.search.Movie

data class MovieResponse(
    @SerializedName("display")
    val display: Int,
    @SerializedName("items")
    val movies: List<Movie>,
    @SerializedName("lastBuildDate")
    val lastBuildDate: String,
    @SerializedName("start")
    val start: Int,
    @SerializedName("total")
    val total: Int
)