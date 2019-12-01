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
    @SerializedName("userRating")
    private val _userRating: String
) {
    val userRating: Float
        get() = _userRating.toFloat() / 2
}

data class ResponseMovies(
    val display: Int,
    val lastBuildDate: String,
    val start: Int,
    val total: Int,
    @SerializedName("items") val movies: List<Movie>
)
