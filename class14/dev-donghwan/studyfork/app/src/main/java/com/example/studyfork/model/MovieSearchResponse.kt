package com.example.studyfork.model


data class MovieSearchResponse(
    val display: Int,
    val movieItems: List<MovieItem>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int,
) {
    data class MovieItem(
        val actor: String,
        val director: String,
        val image: String,
        val link: String,
        val pubDate: String,
        val subtitle: String,
        val title: String,
        val userRating: String,
    )
}

fun MovieSearchResponse.toDomain(): List<MovieSearchResponse.MovieItem> {
    return this.movieItems
}