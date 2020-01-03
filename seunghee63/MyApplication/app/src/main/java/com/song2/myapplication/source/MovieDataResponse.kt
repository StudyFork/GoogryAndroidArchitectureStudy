package com.song2.myapplication.source

data class MovieDataResponse(
    val lastBuildDate: String?,
    val total: Int?,
    val start: Int?,
    val items: List<MovieData>
)