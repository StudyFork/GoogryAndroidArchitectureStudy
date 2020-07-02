package io.github.jesterz91.domain.model

data class Movie(
    val title: String,
    val subtitle: String,
    val director: String,
    val actor: String,
    val image: String,
    val link: String,
    val pubDate: String,
    val userRating: Float
)