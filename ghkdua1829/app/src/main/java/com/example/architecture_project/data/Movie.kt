package com.example.architecture_project.data

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("image")
    val image: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("userRating")
    val rating: Float,
    @SerializedName("pubDate")
    val pubDate:String,
    @SerializedName("director")
    val director: String,
    @SerializedName("actor")
    val actor: String
)