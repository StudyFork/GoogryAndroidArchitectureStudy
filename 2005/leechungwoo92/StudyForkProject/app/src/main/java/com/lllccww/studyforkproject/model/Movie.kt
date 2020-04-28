package com.lllccww.studyforkproject.model

import MovieItem

data class Movie(
    val display: String,
    val items: ArrayList<MovieItem>,
    val lastBuildDate: String,
    val start: String,
    val total: String
)