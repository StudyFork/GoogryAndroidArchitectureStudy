package com.jay.remote.model

import com.google.gson.annotations.SerializedName

data class MovieItem(
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

data class ResponseMovie(
    val movies: List<MovieItem>
)