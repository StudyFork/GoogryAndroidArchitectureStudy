package com.hwaniiidev.architecture.model

data class ResponseMovieSearchData(
    val display: Int,
    val items: List<Item>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)