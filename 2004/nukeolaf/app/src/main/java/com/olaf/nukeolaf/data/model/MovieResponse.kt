package com.olaf.nukeolaf.data.model

data class MovieResponse(
    val lastBuildDate: String,
    val total: String,
    val start: String,
    val display: String,
    val items: List<MovieItem>
)