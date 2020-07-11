package com.project.data.model

data class NaverMovieEntity(
    val items: List<MovieItem>
)

data class MovieItem(
    val title: String,
    val link: String,
    val image: String,
    val subtitle: String,
    val pubDate: String,
    val director: String,
    val actor: String,
    val userRating: Double
)
