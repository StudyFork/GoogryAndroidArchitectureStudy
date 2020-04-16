package com.example.studyforkandroid.data

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val display: Int,
    @SerializedName("items")
    val movie: List<Movie>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)

data class Movie(
    val actor: String,
    val director: String,
    val image: String,
    val link: String,
    val pubDate: String,
    val subtitle: String,
    val title: String,
    val userRating: String
)