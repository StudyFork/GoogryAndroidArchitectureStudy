package com.hwaniiidev.architecture.Model

data class ResponseMovieSearchData(
    val display: Int,
    val items: List<Item>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)