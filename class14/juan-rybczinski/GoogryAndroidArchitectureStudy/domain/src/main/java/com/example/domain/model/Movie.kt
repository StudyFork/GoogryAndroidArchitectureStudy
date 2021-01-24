package com.example.domain.model

data class Movie(
    val title: String,
    override val link: String,
    val image: String,
    val subtitle: String,
    val pubDate: String,
    val director: String,
    val actor: String,
    val userRating: Float
) : UrlResource