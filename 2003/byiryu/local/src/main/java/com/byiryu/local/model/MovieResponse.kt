package com.byiryu.local.model

data class MovieResponse(
    val total: Int,
    val start: Int,
    val display: Int,
    val lastBuildDate: String,
    val items: List<MovieItem>
)