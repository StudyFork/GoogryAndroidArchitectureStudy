package com.example.googryandroidarchitecturestudy.model

data class MovieResponse(
    val display: Int,
    val items: List<Movie>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)