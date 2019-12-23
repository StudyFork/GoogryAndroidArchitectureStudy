package com.onit.googlearchitecturestudy

import com.google.gson.annotations.SerializedName

data class Movies(
    @SerializedName("items") val movies: List<Movie>
)

data class Movie(
    @SerializedName("image") val posterImage: String,
    @SerializedName("title") val title: String,
    @SerializedName("userRating") val userRating: Float,
    @SerializedName("pubDate") val releaseDate: String,
    @SerializedName("director") val director: String,
    @SerializedName("actor") val actor: String,
    @SerializedName("link") val link: String
)