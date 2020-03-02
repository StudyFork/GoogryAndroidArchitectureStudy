package com.mtjin.androidarchitecturestudy.data


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mtjin.androidarchitecturestudy.data.Movie

data class MovieResponse(
    @SerializedName("display")
    @Expose
    val display: Int,
    @SerializedName("items")
    @Expose
    val movies: List<Movie>,
    @SerializedName("lastBuildDate")
    @Expose
    val lastBuildDate: String,
    @SerializedName("start")
    @Expose
    val start: Int,
    @SerializedName("total")
    val total: Int
)