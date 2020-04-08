package com.eunice.eunicehong.data.remote

import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("items") val items: List<Movie>
)

data class Movie(
    @SerializedName("title") val title: String,
    @SerializedName("link") val link: String,
    @SerializedName("image") val imageUrl: String,
    @SerializedName("subtitle") val subtitle: String,
    @SerializedName("pubDate") val pubDate: String,
    @SerializedName("director") val directors: String,
    @SerializedName("actor") val actors: String,
    @SerializedName("userRating") val userRating: String
)