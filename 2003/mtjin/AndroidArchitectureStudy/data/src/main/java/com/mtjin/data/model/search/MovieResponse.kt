package com.mtjin.data.model.search

data class MovieResponse(
    val display: Int,
    val movies: List<Movie>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)