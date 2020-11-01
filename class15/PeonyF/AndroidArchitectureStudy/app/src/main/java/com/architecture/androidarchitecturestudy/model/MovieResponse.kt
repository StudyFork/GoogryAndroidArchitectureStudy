package com.architecture.androidarchitecturestudy.model

data class MovieResponse(
    val lastBuildDat: String,
    val total: String,
    val start: String,
    val display: String,
    val items: List<Movie>
)