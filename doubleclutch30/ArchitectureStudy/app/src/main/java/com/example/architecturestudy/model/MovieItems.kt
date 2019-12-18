package com.example.architecturestudy.model

import com.google.gson.annotations.SerializedName

data class MovieData(val items: List<MovieItems>)

data class MovieItems(


    @SerializedName("title")
    val title: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("year")
    val year: String,
    @SerializedName("director")
    val director: String,
    @SerializedName("actor")
    val actor: String,
    @SerializedName("userRating")
    val rating: Float
)