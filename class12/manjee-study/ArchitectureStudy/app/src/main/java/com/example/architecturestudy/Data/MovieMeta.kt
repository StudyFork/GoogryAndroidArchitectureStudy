package com.example.architecturestudy.Data

import com.example.architecturestudy.Data.MovieData

data class MovieMeta(
    val display: Int,
    val movieData: List<MovieData>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)