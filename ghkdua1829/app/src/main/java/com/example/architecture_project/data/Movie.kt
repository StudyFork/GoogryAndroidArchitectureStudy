package com.example.architecture_project.data

import com.google.gson.annotations.SerializedName

data class Movie(
    val image: String,
    val title: String,
    @SerializedName("userRating")
    val rating: Float,
    val pubDate:String,
    val director: String,
    val actor: String,
    val link:String
)