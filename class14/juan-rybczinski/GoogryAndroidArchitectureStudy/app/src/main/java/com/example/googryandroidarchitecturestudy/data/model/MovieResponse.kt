package com.example.googryandroidarchitecturestudy.data.model

data class MovieResponse(
    val display: Int,
    val items: List<Movie>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
) {
    data class Movie(
        val title: String,
        val image: String,
        val subtitle: String,
        val pubDate: String,
        val director: String,
        val actor: String,
        val userRating: String
    )
}