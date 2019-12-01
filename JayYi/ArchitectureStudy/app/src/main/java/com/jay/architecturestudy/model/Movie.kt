package com.jay.architecturestudy.model

import com.google.gson.annotations.SerializedName

data class Movie(
    val title: String,
    val link: String,
    val image: String,
    val subtitle: String,
    val director: String,
    val actor: String,
    val pubDate: String,
    val userRating: String
)

data class ResponseMovies(
    val display: Int,
    val lastBuildDate: String,
    val start: Int,
    val total: Int,
    @SerializedName("items") val movies: List<Movie>
)
