package com.olaf.nukeolaf

data class MovieResponse(
    val lastBuildDate: String,
    val total: String,
    val start: String,
    val display: String,
    val items: ArrayList<MovieItem>
)