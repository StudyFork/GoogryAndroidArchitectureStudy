package com.example.dkarch.data.response

import com.example.dkarch.data.entity.Movie

data class MovieResponse(
    val display: Int,
    val items: ArrayList<Movie>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)
