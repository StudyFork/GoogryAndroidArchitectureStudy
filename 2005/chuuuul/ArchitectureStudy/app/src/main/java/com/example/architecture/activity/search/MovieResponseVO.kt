package com.example.architecture.activity.search


data class MovieResponseVO(
    val display: Int,
    val movies: List<MovieVO>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)