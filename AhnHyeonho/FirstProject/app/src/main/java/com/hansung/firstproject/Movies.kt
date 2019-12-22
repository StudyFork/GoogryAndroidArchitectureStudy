package com.hansung.firstproject

data class Movies(
    val lastBuildDate: String,
    val total: Long,
    val start: Int,
    val display: Int,
    val items: List<Movie>
)

data class Movie(
    val title: String,
    val link: String,
    val image: String,
    val subtitle: String,
    val pubDate: String,
    val director: String,
    val actor: String,
    val userRating: String
)