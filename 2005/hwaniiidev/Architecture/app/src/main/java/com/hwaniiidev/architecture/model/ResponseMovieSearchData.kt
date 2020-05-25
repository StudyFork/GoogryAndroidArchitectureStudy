package com.hwaniiidev.architecture.model

data class ResponseMovieSearchData(
    var query : String,
    val display: Int,
    val items: ArrayList<Item>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)