package com.example.architecture.data.model

import com.google.gson.annotations.SerializedName


data class MovieResponseModel(
    val display: Int,
    @SerializedName("items")
    val movies: List<MovieModel>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)