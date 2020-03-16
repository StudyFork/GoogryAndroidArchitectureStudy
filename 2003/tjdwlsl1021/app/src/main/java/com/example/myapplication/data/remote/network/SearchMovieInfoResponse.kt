package com.example.myapplication.data.remote.network

import com.example.myapplication.data.local.MovieEntity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SearchMovieInfoResponse (
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
    val items: List<MovieEntity>
)