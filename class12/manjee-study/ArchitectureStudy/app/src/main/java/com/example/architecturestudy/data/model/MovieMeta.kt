package com.example.architecturestudy.data.model

import com.example.architecturestudy.data.model.MovieData

data class MovieMeta(
    val display: Int,
    val items: List<MovieData>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)