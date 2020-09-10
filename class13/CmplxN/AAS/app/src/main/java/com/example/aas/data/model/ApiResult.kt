package com.example.aas.data.model

import com.google.gson.annotations.SerializedName

data class ApiResult(
    val display: Int,
    @SerializedName("items")
    val movies: List<Movie>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)