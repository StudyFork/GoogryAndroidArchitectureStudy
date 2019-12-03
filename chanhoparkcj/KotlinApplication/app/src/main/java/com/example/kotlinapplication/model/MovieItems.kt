package com.example.kotlinapplication.model

import com.google.gson.annotations.SerializedName

data class MovieItems(
    val title: String,
    val link: String,
    val image: String,
    val subtitle: String,
    val pubDate: String,
    val director: String,
    val actor: String,
    val userRating: Float
)