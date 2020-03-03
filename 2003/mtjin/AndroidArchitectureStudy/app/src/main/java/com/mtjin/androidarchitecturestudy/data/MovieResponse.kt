package com.mtjin.androidarchitecturestudy.data


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mtjin.androidarchitecturestudy.data.Movie

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