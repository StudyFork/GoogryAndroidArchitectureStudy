package com.chul.data.model

import com.google.gson.annotations.SerializedName


data class MovieResponseModel(
    val display: Int,
    @SerializedName("items")
    val movieList: List<MovieModel>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)