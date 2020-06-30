package com.example.architecturestudy.data

data class MovieMeta(
    val display: Int,
    val items: List<MovieData>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)