package com.example.googryandroidarchitecturestudy.network

import com.example.googryandroidarchitecturestudy.domain.Movie

data class NetworkMovie(
    val display: Int,
    val items: List<Movie>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)