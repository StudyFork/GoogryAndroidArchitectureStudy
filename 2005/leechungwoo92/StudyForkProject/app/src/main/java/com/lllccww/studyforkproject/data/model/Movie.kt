package com.lllccww.studyforkproject.data.model


data class Movie(
    val display: String,
    val items: ArrayList<MovieItem>,
    val lastBuildDate: String,
    val start: String,
    val total: String
)