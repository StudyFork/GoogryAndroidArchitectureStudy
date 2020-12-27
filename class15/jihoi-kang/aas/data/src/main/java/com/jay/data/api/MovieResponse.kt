package com.jay.data.api

import com.jay.data.model.Movie

data class MovieResponse(
    val display: Int,
    val items: List<Movie>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)