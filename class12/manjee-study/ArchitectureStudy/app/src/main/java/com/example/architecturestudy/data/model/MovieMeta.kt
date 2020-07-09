package com.example.architecturestudy.data.model

data class MovieMeta(
    val display: Int,
    val items: List<MovieData>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)