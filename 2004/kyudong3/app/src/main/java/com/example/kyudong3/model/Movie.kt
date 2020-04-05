package com.example.kyudong3.model

data class MovieList(
    val total: Int,
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
    val userRating: Double
)