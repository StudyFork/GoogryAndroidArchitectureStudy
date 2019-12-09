package com.example.androidarchitecture.models

import com.google.gson.annotations.SerializedName

data class MovieData(
    @SerializedName("title")
    val title: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("pubDate")
    val pubDate: String,
    @SerializedName("director")
    val director: String,
    @SerializedName("actor")
    val actor: String,
    @SerializedName("userRating")
    val origin_userRating: Float
) {
    val userRating: Float
        get() = origin_userRating / 2
}