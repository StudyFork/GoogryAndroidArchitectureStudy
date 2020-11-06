package com.jay.aas.api

import com.jay.aas.model.Movie

data class MovieResponse(
    val display: Int,
    val items: List<Movie>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)